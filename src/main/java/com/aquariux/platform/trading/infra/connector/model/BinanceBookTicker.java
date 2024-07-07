package com.aquariux.platform.trading.infra.connector.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BinanceBookTicker {
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("bidPrice")
    private BigDecimal bidPrice;
    @JsonProperty("bidQty")
    private BigDecimal bidQty;
    @JsonProperty("askPrice")
    private BigDecimal askPrice;
    @JsonProperty("askQty")
    private BigDecimal askQty;
}
