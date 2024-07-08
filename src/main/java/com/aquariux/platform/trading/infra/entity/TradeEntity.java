package com.aquariux.platform.trading.infra.entity;

import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.domain.TradeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trade")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;

    @Enumerated(EnumType.STRING)
    private SupportedSymbol symbol;

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;

    private String status;

    private BigDecimal quantity;

    private BigDecimal price;

    private LocalDateTime timeStamp;
}
