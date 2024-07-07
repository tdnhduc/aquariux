package com.aquariux.platform.trading.infra.repository;

import com.aquariux.platform.trading.infra.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<WalletEntity, String> {
}
