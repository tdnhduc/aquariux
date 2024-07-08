package com.aquariux.platform.trading.controller.mapper;

import com.aquariux.platform.trading.controller.dto.WalletResponse;
import com.aquariux.platform.trading.service.domain.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletResponseMapper {
    WalletResponseMapper INSTANCE = Mappers.getMapper(WalletResponseMapper.class);

    @Mapping(target = "userId", source = "userId", qualifiedByName = "maskSensitiveData")
    @Mapping(target = "walletId", source = "walletId", qualifiedByName = "maskSensitiveData")
    WalletResponse toResponse(Wallet wallet);

    @Named("maskSensitiveData")
    default String maskSensitiveData(String data) {
        return data.replaceAll("(.{4})(.*)(.{4})", "$1****$3");
    }
}
