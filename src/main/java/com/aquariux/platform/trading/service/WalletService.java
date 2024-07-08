package com.aquariux.platform.trading.service;

import com.aquariux.platform.trading.domain.Currency;
import com.aquariux.platform.trading.service.domain.Wallet;

public interface WalletService {
    Wallet getWalletByUserId(String userId, Currency currency);
}
