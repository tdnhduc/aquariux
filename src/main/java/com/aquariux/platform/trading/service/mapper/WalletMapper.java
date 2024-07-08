package com.aquariux.platform.trading.service.mapper;

import com.aquariux.platform.trading.infra.entity.WalletEntity;
import com.aquariux.platform.trading.service.domain.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper {
    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    @Mapping(source = "walletEntity.userEntity.id", target = "userId")
    @Mapping(source = "walletEntity.id", target = "walletId")
    Wallet toWallet(WalletEntity walletEntity);
}
