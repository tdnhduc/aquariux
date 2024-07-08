package com.aquariux.platform.trading.infra.repository;

import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.infra.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, String> {
    @Query("SELECT t FROM TradeEntity t WHERE t.userId = :userId " +
            "AND t.symbol = :symbol " +
            "AND t.timeStamp >= :from AND t.timeStamp <= :to ORDER BY t.timeStamp ASC " +
            "LIMIT 50 OFFSET 0 ")
        // hard code limit and offset. TODO: add pagination
    List<TradeEntity> getHistory(@Param("userId") String userId, @Param("symbol") SupportedSymbol symbol,
                                 @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
