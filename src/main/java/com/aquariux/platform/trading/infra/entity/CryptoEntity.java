package com.aquariux.platform.trading.infra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "crypto")
@Getter
@Builder
public class CryptoEntity {
    @Id
    private Long id;

    private String symbol;
}
