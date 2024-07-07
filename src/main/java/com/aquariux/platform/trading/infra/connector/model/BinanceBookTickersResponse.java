package com.aquariux.platform.trading.infra.connector.model;

import lombok.*;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@NoArgsConstructor
@ToString
public class BinanceBookTickersResponse extends ArrayList<BinanceBookTicker> {
}
