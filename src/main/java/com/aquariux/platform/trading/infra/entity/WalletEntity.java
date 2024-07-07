package com.aquariux.platform.trading.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
@Getter
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class WalletEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "symbol")
    private CryptoEntity crypto;

    private BigDecimal balance;
}
