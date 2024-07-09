package com.aquariux.platform.trading.service;

import com.aquariux.platform.trading.domain.Partner;
import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.domain.TradeType;
import com.aquariux.platform.trading.infra.connector.BinanceConnector;
import com.aquariux.platform.trading.infra.connector.HuobiConnector;
import com.aquariux.platform.trading.infra.entity.AggregatedPriceEntity;
import com.aquariux.platform.trading.infra.repository.AggregatedPriceRepository;
import com.aquariux.platform.trading.service.impl.AggregatedPriceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AggregatedPriceServiceImplTest {
    @Mock
    private BinanceConnector binanceConnector;
    private HuobiConnector huobiConnector;
    @Mock
    private AggregatedPriceRepository repository;

    @InjectMocks
    private AggregatedPriceServiceImpl aggregatedPriceService;

    @Test
    public void shouldGetBestPrice() {
        var entity = AggregatedPriceEntity.builder()
                .partnerName(Partner.BINANCE)
                .symbol(SupportedSymbol.BTCUSDT)
                .bidPrice(BigDecimal.ONE)
                .bidQty(BigDecimal.ONE)
                .askPrice(BigDecimal.TEN)
                .askQty(BigDecimal.TEN)
                .build();
        when(repository.findAllById(any())).thenReturn(Collections.singletonList(entity));
        var bestPrice = this.aggregatedPriceService.getBestPrice(SupportedSymbol.BTCUSDT, TradeType.LONG);
        // assert bestPrice
        assertEquals(BigDecimal.ONE, bestPrice.getBidPrice());
        assertEquals(Partner.BINANCE, bestPrice.getPartnerName());
    }
}
