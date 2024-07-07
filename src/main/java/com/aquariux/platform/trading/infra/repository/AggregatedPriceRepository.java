package com.aquariux.platform.trading.infra.repository;

import com.aquariux.platform.trading.infra.entity.AggregatedPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AggregatedPriceRepository extends JpaRepository<AggregatedPriceEntity, Long> {
}
