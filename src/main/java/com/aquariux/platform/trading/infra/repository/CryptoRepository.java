package com.aquariux.platform.trading.infra.repository;

import com.aquariux.platform.trading.infra.entity.CryptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoEntity, String> {
}
