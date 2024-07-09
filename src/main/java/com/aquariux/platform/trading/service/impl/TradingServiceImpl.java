package com.aquariux.platform.trading.service.impl;

import com.aquariux.platform.trading.controller.dto.OrderHistory;
import com.aquariux.platform.trading.controller.dto.OrderHistoryResponse;
import com.aquariux.platform.trading.domain.Currency;
import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.domain.TradeType;
import com.aquariux.platform.trading.infra.entity.TradeEntity;
import com.aquariux.platform.trading.infra.entity.WalletEntity;
import com.aquariux.platform.trading.infra.exception.BusinessException;
import com.aquariux.platform.trading.infra.repository.TradeRepository;
import com.aquariux.platform.trading.infra.repository.WalletRepository;
import com.aquariux.platform.trading.service.DistributedLockService;
import com.aquariux.platform.trading.service.TradingService;
import com.aquariux.platform.trading.service.domain.OrderPlace;
import com.aquariux.platform.trading.service.mapper.TradeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Log4j2
public class TradingServiceImpl implements TradingService {
    private WalletRepository walletRepository;
    private DistributedLockService distributedLockService;
    private TradeRepository tradeRepository;

    @Override
    @Transactional
    public OrderPlace placeOrder(String userId, SupportedSymbol symbol, TradeType tradeType, BigDecimal price,
                                 BigDecimal quantity) throws Exception {

        try (var ignored = this.distributedLockService.tryLock(userId, 10, TimeUnit.SECONDS)){
            var wallet = this.getAndValidateWallet(userId,
                    tradeType.equals(TradeType.LONG) ? symbol.getLongCurrency() : symbol.getShortCurrency());
            var minusAmount = price.multiply(quantity);
            if (!this.isSufficientBalance(wallet.getBalance(), minusAmount)) {
                throw new BusinessException("Insufficient balance");
            }
            wallet.setBalance(wallet.getBalance().subtract(minusAmount));
            TradeEntity trade = TradeEntity.builder()
                    .userId(userId)
                    .symbol(symbol)
                    .tradeType(tradeType)
                    .price(price)
                    .quantity(quantity)
                    .status("success")
                    .timeStamp(LocalDateTime.now())
                    .build();
            wallet = this.walletRepository.saveAndFlush(wallet);
            var tradeEntity =this.tradeRepository.saveAndFlush(trade);
            log.info("Trade {} has been placed", tradeEntity.getId());
            return OrderPlace.builder()
                    .orderId(tradeEntity.getId())
                    .balance(wallet.getBalance())
                    .newBalance(wallet.getBalance())
                    .timeStamp(tradeEntity.getTimeStamp().toEpochSecond(ZoneOffset.UTC))
                    .status(tradeEntity.getStatus())
                    .build();
        } finally {
            distributedLockService.unlock(userId);
        }

    }

    @Override
    public List<OrderHistoryResponse> getHistory(OrderHistory history) {
        var entities = this.tradeRepository.getHistory(history.getUserId(), history.getSymbol(), history.getFrom(), history.getTo());
        return entities.stream().map(TradeMapper.INSTANCE::toOrderPlace).toList();

    }

    private WalletEntity getAndValidateWallet(String userId, Currency currency) {
        var wallet =  this.walletRepository.findByUserIdAndCurrency(userId, currency);
        if (wallet == null) {
            throw new BusinessException("Wallet not found");
        }
        return wallet;
    }

    private boolean isSufficientBalance(BigDecimal balance, BigDecimal minusAmount) {
        return balance.compareTo(minusAmount) >= 0;
    }
}
