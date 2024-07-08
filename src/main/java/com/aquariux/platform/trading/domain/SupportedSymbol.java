package com.aquariux.platform.trading.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@ToString
@Getter
public enum SupportedSymbol {
    BTCUSDT(Currency.BTC, Currency.USDT),
    ETHUSDT(Currency.ETH, Currency.USDT);
    private static final Map<String, Boolean> SUPPORTED_SYMBOL_BOOLEAN_MAP;

    static {
        var tmpMap = new HashMap<String, Boolean>();
        for (var symbol: SupportedSymbol.values()) {
            tmpMap.put(symbol.name(), true);
        }
        SUPPORTED_SYMBOL_BOOLEAN_MAP = Collections.unmodifiableMap(tmpMap);
    }

    private final Currency longCurrency;
    private final Currency shortCurrency;

    public static boolean isSupportedSymbol(String symbol) {
        return SUPPORTED_SYMBOL_BOOLEAN_MAP.containsKey(symbol.toUpperCase());
    }
}
