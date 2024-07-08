package com.aquariux.platform.trading.controller.dto;

import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.domain.TradeType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {
    private String userId;
    private LocalDateTime from;
    private LocalDateTime to;
    private SupportedSymbol symbol;
    private TradeType tradeType;
    private int limit;
    private int offset;
    private String sort;
}
