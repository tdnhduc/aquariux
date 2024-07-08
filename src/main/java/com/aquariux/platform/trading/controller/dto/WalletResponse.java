package com.aquariux.platform.trading.controller.dto;

import com.aquariux.platform.trading.domain.Currency;
import lombok.*;

@Data
@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponse {
    private String userId;
    private String walletId;
    private String balance;
    private Currency currency;

}
