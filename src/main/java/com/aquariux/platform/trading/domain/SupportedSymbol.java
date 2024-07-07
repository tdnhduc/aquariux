package com.aquariux.platform.trading.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum SupportedSymbol {
    BTCUSDT,
    ETHUSDT;
    private static final Map<String, Boolean> SUPPORTED_SYMBOL_BOOLEAN_MAP;

    static {
        var tmpMap = new HashMap<String, Boolean>();
        for (var symbol: SupportedSymbol.values()) {
            tmpMap.put(symbol.name(), true);
        }
        SUPPORTED_SYMBOL_BOOLEAN_MAP = Collections.unmodifiableMap(tmpMap);
    }

    public static boolean isSupportedSymbol(String symbol) {
        return SUPPORTED_SYMBOL_BOOLEAN_MAP.containsKey(symbol.toUpperCase());
    }
}
