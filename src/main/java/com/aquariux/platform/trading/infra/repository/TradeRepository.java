package com.aquariux.platform.trading.infra.repository;

import com.aquariux.platform.trading.infra.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<TradeEntity, String> {
}
