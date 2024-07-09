package com.aquariux.platform.trading.service.domain;

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
