package com.aquariux.platform.trading.service;

import com.aquariux.platform.trading.controller.dto.OrderHistory;
import com.aquariux.platform.trading.controller.dto.OrderHistoryResponse;
import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.domain.TradeType;
import com.aquariux.platform.trading.service.domain.OrderPlace;

import java.math.BigDecimal;
import java.util.List;

public interface TradingService {
    OrderPlace placeOrder(String userId, SupportedSymbol symbol, TradeType tradeType, BigDecimal price,
                          BigDecimal quantity) throws Exception;
    List<OrderHistoryResponse> getHistory(OrderHistory history) throws Exception;
}
