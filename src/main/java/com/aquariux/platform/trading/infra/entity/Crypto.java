package com.aquariux.platform.trading.infra.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Entity
@Table(name = "crypto")
@Getter
@Builder
public class Crypto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String partnerId;

    private String symbolId;

    private String name;
}
