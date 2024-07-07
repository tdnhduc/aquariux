package com.aquariux.platform.trading.infra.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "aggregated_price")
public class AggregatedPrice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crypto_id")
    private Crypto crypto;

    private BigDecimal bidPrice;

    private BigDecimal askPrice;

}
