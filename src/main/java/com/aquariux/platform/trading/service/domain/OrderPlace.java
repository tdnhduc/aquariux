package com.aquariux.platform.trading.service.domain;

import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.domain.TradeType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Data
@Getter
@ToString
public class OrderPlace {
    private String orderId;
    private BigDecimal balance;
    private BigDecimal newBalance;
    private String status;
    private long timeStamp;
}
