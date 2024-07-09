package com.aquariux.platform.trading.service.mapper;

import com.aquariux.platform.trading.controller.dto.OrderHistoryResponse;
import com.aquariux.platform.trading.infra.entity.TradeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper
public interface TradeMapper {
    TradeMapper INSTANCE = Mappers.getMapper(TradeMapper.class);

    @Mapping(target = "timeStamp", source = "timeStamp", qualifiedByName = "toTimeStamp")
    @Mapping(target = "orderId", source = "id")
    OrderHistoryResponse toOrderPlace(TradeEntity tradeEntity);

    @Named("toTimeStamp")
    default long toTimeStamp(LocalDateTime timeStamp) {
        return timeStamp.toEpochSecond(ZoneOffset.UTC);
    }
}
