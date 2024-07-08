package com.aquariux.platform.trading.service.internal;

import com.aquariux.platform.trading.domain.Currency;
import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.infra.entity.CryptoEntity;
import com.aquariux.platform.trading.infra.entity.UserEntity;
import com.aquariux.platform.trading.infra.entity.WalletEntity;
import com.aquariux.platform.trading.infra.repository.CryptoRepository;
import com.aquariux.platform.trading.infra.repository.UserRepository;
import com.aquariux.platform.trading.infra.repository.WalletRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Log4j2
public class LoadData {
    private UserRepository userRepository;
    private WalletRepository walletRepository;
    private CryptoRepository cryptoRepository;
    @PostConstruct
    @Transactional
    public void loadData() {
        // crypto
        CryptoEntity btcusdt = CryptoEntity.builder()
                .id(1L)
                .symbol(SupportedSymbol.BTCUSDT)
                .build();
        CryptoEntity ethusdt = CryptoEntity.builder()
                .id(2L)
                .symbol(SupportedSymbol.ETHUSDT)
                .build();
        this.cryptoRepository.saveAll(Stream.of(btcusdt, ethusdt).toList());
        // user
        var user = userRepository.save(UserEntity.builder()
                .id("1234567890")
                .email("duc@gmail.com")
                .userName("duc")
                .build());
        // wallet
        var btcWallet = WalletEntity.builder()
                .id("1234567890")
                .balance(new BigDecimal("50000"))
                .currency(Currency.BTC)
                .userEntity(user)
                .build();
        var ethWallet = WalletEntity.builder()
                .id("1234567890")
                .balance(new BigDecimal("50000"))
                .currency(Currency.ETH)
                .userEntity(user)
                .build();
        var usdtWallet = WalletEntity.builder()
                .id("1234567890")
                .balance(new BigDecimal("50000"))
                .currency(Currency.USDT)
                .userEntity(user)
                .build();

        this.walletRepository.saveAll(Stream.of(btcWallet, ethWallet, usdtWallet).toList());
        log.info("load data");
    }
}
