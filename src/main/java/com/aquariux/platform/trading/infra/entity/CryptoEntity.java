package com.aquariux.platform.trading.infra.entity;

import com.aquariux.platform.trading.domain.Currency;
import com.aquariux.platform.trading.domain.SupportedSymbol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "crypto")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CryptoEntity {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private SupportedSymbol symbol;
}
