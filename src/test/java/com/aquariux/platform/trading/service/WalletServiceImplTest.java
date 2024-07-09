package com.aquariux.platform.trading.service;

import com.aquariux.platform.trading.domain.Currency;
import com.aquariux.platform.trading.infra.entity.WalletEntity;
import com.aquariux.platform.trading.infra.repository.WalletRepository;
import com.aquariux.platform.trading.service.impl.WalletServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WalletServiceImplTest {
    @Mock
    private WalletRepository walletRepository;
    @InjectMocks
    private WalletServiceImpl walletService;

    @Test
    void shouldReturnWallet() {
        when(walletRepository.findWalletByUserId("123", Currency.USDT)).thenReturn(new WalletEntity());
        var wallet = walletService.getWalletByUserId("123", Currency.USDT);
        // assert wallet
        assertNotNull(wallet);
    }
}
