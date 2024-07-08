package com.aquariux.platform.trading.controller.dto;

import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.domain.TradeType;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OderRequest {
    private SupportedSymbol symbol;
    private TradeType tradeType;
    private BigDecimal price;
    private BigDecimal quantity;
}
