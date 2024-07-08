package com.aquariux.platform.trading.service;

import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.domain.TradeType;
import com.aquariux.platform.trading.service.domain.AggregatedPrice;

public interface AggregatedPriceService {
    AggregatedPrice getBestPrice(SupportedSymbol symbol, TradeType tradeType);
}
