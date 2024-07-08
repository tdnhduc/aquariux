package com.aquariux.platform.trading.infra.entity;

import com.aquariux.platform.trading.domain.Currency;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@IdClass(WalletEntity.WalletEntityKey.class)
public class WalletEntity extends BaseEntity {
    @Id
    private String id;
    @Id
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    private BigDecimal balance;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WalletEntityKey implements Serializable {
        private String id;
        private Currency currency;
    }
}
