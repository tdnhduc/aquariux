package com.aquariux.platform.trading.service.domain;

import com.aquariux.platform.trading.domain.Currency;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Wallet {
    private String userId;
    private String walletId;
    private BigDecimal balance;
    private Currency currency;
}
