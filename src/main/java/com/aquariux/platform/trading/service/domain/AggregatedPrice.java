package com.aquariux.platform.trading.service.domain;

import com.aquariux.platform.trading.domain.Partner;
import com.aquariux.platform.trading.domain.SupportedSymbol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class AggregatedPrice {
    private SupportedSymbol symbol;
    private Partner partnerName;
    private BigDecimal bidPrice;
    private BigDecimal bidQty;
    private BigDecimal askPrice;
    private BigDecimal askQty;

}
