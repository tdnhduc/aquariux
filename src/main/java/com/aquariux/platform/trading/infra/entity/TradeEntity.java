package com.aquariux.platform.trading.infra.entity;

import com.aquariux.platform.trading.domain.TradeType;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trade")
@Getter
public class TradeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "symbol")
    private CryptoEntity crypto;

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;

    private BigDecimal quantity;

    private BigDecimal price;

    private LocalDateTime timeStamp;
}
