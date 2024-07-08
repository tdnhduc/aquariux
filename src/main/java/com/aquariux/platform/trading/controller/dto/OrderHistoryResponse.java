package com.aquariux.platform.trading.controller.dto;

import com.aquariux.platform.trading.domain.SupportedSymbol;
import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryResponse {
    private String orderId;
    private SupportedSymbol symbol;
    private String tradeType;
    private String quantity;
    private String price;
    private String timeStamp;
    private String status;
}
