package com.aquariux.platform.trading.service.impl;

import com.aquariux.platform.trading.domain.Partner;
import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.domain.TradeType;
import com.aquariux.platform.trading.infra.connector.BinanceConnector;
import com.aquariux.platform.trading.infra.connector.HuobiConnector;
import com.aquariux.platform.trading.infra.entity.AggregatedPriceEntity;
import com.aquariux.platform.trading.infra.entity.WalletEntity;
import com.aquariux.platform.trading.infra.exception.BusinessException;
import com.aquariux.platform.trading.infra.repository.AggregatedPriceRepository;
import com.aquariux.platform.trading.infra.repository.UserRepository;
import com.aquariux.platform.trading.infra.repository.WalletRepository;
import com.aquariux.platform.trading.service.AggregatedPriceService;
import com.aquariux.platform.trading.service.domain.AggregatedPrice;
import com.aquariux.platform.trading.service.domain.OrderPlace;
import com.aquariux.platform.trading.service.mapper.AggregatedPriceMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service
@Log4j2
@AllArgsConstructor
public class AggregatedPriceServiceImpl implements AggregatedPriceService {

    private final BinanceConnector binanceConnector;
    private final HuobiConnector huobiConnector;
    private final AggregatedPriceRepository aggregatedPriceRepository;

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    private void getPrice() throws JsonProcessingException {
        log.debug("Begin get latest price");
        //TODO: Enhance this by getting the price ticker from two sources simultaneously.
        var binancePricesEntities = this.binanceConnector.getPrices()
                .stream()
                .filter(binanceBookTicker -> SupportedSymbol.isSupportedSymbol(binanceBookTicker.getSymbol()))
                .map(binanceBookTicker ->
                    AggregatedPriceEntity.builder()
                            .partnerName(Partner.BINANCE)
                            .symbol(SupportedSymbol.valueOf(binanceBookTicker.getSymbol().toUpperCase()))
                            .bidPrice(binanceBookTicker.getBidPrice())
                            .bidQty(binanceBookTicker.getBidQty())
                            .askPrice(binanceBookTicker.getAskPrice())
                            .askQty(binanceBookTicker.getAskQty())
                            .build()
                )
                .toList();
        var huobiBookTickersPricesEntities = this.huobiConnector.getPrices().getData()
                .stream()
                .filter(huobiBookTicker -> SupportedSymbol.isSupportedSymbol(huobiBookTicker.getSymbol()))
                .map(huobiBookTicker ->
                        AggregatedPriceEntity.builder()
                                .partnerName(Partner.HUOBI)
                                .symbol(SupportedSymbol.valueOf(huobiBookTicker.getSymbol().toUpperCase()))
                                .bidPrice(huobiBookTicker.getBid())
                                .bidQty(huobiBookTicker.getBidSize())
                                .askPrice(huobiBookTicker.getAsk())
                                .askQty(huobiBookTicker.getAskSize())
                                .build()
                )
                .toList();

        this.aggregatedPriceRepository.saveAllAndFlush(
                Stream.concat(binancePricesEntities.stream(), huobiBookTickersPricesEntities.stream()).toList());
        log.debug("End get latest price");
    }

    @Override
    public AggregatedPrice getBestPrice(SupportedSymbol symbol, TradeType tradeType) throws BusinessException {
        var binancePrice = AggregatedPriceEntity.AggregatedPriceId.builder()
                .symbol(symbol)
                .partnerName(Partner.BINANCE)
                .build();
        var huobiPrice = AggregatedPriceEntity.AggregatedPriceId.builder()
                .symbol(symbol)
                .partnerName(Partner.HUOBI)
                .build();
        var entities = this.aggregatedPriceRepository.findAllById(Stream.of(binancePrice, huobiPrice).toList());
        if (entities.isEmpty()) {
            throw new BusinessException("Symbol not found");
        }
        var maxPrice = Collections.max(entities, Comparator.comparing(entity -> {
            if (TradeType.LONG.equals(tradeType)) {
                return entity.getBidPrice();
            } else {
                return entity.getAskPrice();
            }
        }));
        log.info("Max price: {}", maxPrice.toString());
        return AggregatedPriceMapper.INSTANCE.toDomain(maxPrice);
    }
}
