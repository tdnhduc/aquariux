package com.aquariux.platform.trading.service.mapper;

import com.aquariux.platform.trading.infra.entity.AggregatedPriceEntity;
import com.aquariux.platform.trading.service.domain.AggregatedPrice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AggregatedPriceMapper {
    AggregatedPriceMapper INSTANCE = Mappers.getMapper(AggregatedPriceMapper.class);

    AggregatedPrice toDomain(AggregatedPriceEntity entity);
}
