package com.aquariux.platform.trading.service.impl;

import com.aquariux.platform.trading.domain.Currency;
import com.aquariux.platform.trading.infra.exception.BusinessException;
import com.aquariux.platform.trading.infra.repository.WalletRepository;
import com.aquariux.platform.trading.service.WalletService;
import com.aquariux.platform.trading.service.domain.Wallet;
import com.aquariux.platform.trading.service.mapper.WalletMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;

    @Override
    public Wallet getWalletByUserId(String userId, Currency currency) {
        var entity = this.walletRepository.findWalletByUserId(userId, currency);
        if (entity == null) {
            throw new BusinessException("Wallet not found");
        }
        return WalletMapper.INSTANCE.toWallet(entity);
    }
}
