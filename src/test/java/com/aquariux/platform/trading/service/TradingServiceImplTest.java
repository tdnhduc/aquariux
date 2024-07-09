package com.aquariux.platform.trading.service;

import com.aquariux.platform.trading.domain.Currency;
import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.domain.TradeType;
import com.aquariux.platform.trading.infra.entity.TradeEntity;
import com.aquariux.platform.trading.infra.entity.UserEntity;
import com.aquariux.platform.trading.infra.entity.WalletEntity;
import com.aquariux.platform.trading.infra.repository.TradeRepository;
import com.aquariux.platform.trading.infra.repository.WalletRepository;
import com.aquariux.platform.trading.service.impl.TradingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TradingServiceImplTest {
    @Mock
    private DistributedLockService distributedLockService;
    @Mock
    private WalletRepository walletRepository;
    @Mock
    private TradeRepository tradeRepository;
    @InjectMocks
    private TradingServiceImpl tradingService;

    @Test
    void shouldPlaceOrderLongSuccessfully() throws Exception {
        //given
        WalletEntity walletEntity = WalletEntity.builder()
                .currency(Currency.BTC)
                .balance(BigDecimal.TEN)
                .id("123")
                .userEntity(UserEntity.builder().id("123").build())
                .build();
        TradeEntity tradeEntity = TradeEntity.builder()
                .status("success")
                .timeStamp(LocalDateTime.now())
                .id("123")
                .tradeType(TradeType.LONG)
                .build();
        when(distributedLockService.tryLock(anyString(), anyLong(), any())).thenReturn(() -> {});
        when(walletRepository.findByUserIdAndCurrency(anyString(), any(Currency.class))).thenReturn(walletEntity);
        when(walletRepository.saveAndFlush(any(WalletEntity.class))).thenReturn(walletEntity);
        when(tradeRepository.saveAndFlush(any(TradeEntity.class))).thenReturn(tradeEntity);

        var order = tradingService.placeOrder("123", SupportedSymbol.BTCUSDT, TradeType.LONG, BigDecimal.ONE, BigDecimal.ONE);
        assertEquals("success", order.getStatus());
    }

    @Test
    void shouldPlaceOrderShortSuccessfully() throws Exception {
        //given
        WalletEntity walletEntity = WalletEntity.builder()
                .currency(Currency.USDT)
                .balance(BigDecimal.TEN)
                .id("123")
                .userEntity(UserEntity.builder().id("123").build())
                .build();
        TradeEntity tradeEntity = TradeEntity.builder()
                .status("success")
                .timeStamp(LocalDateTime.now())
                .id("123")
                .tradeType(TradeType.LONG)
                .build();
        when(distributedLockService.tryLock(anyString(), anyLong(), any())).thenReturn(() -> {});
        when(walletRepository.findByUserIdAndCurrency(anyString(), any(Currency.class))).thenReturn(walletEntity);
        when(walletRepository.saveAndFlush(any(WalletEntity.class))).thenReturn(walletEntity);
        when(tradeRepository.saveAndFlush(any(TradeEntity.class))).thenReturn(tradeEntity);

        var order = tradingService.placeOrder("123", SupportedSymbol.BTCUSDT, TradeType.SHORT, BigDecimal.ONE, BigDecimal.ONE);
        assertEquals("success", order.getStatus());
    }
}
