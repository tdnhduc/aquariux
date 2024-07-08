package com.aquariux.platform.trading.infra.entity;

import com.aquariux.platform.trading.domain.Partner;
import com.aquariux.platform.trading.domain.SupportedSymbol;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "aggregated_price")
@Builder
@IdClass(AggregatedPriceEntity.AggregatedPriceId.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AggregatedPriceEntity extends BaseEntity {

    @Id
    @Enumerated(EnumType.STRING)
    private SupportedSymbol symbol;

    @Id
    @Enumerated(EnumType.STRING)
    private Partner partnerName;

    private BigDecimal bidPrice;

    private BigDecimal bidQty;

    private BigDecimal askPrice;

    private BigDecimal askQty;

    @Data
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AggregatedPriceId implements Serializable {
        private SupportedSymbol symbol;
        private Partner partnerName;
    }
}
