package com.aquariux.platform.trading.infra.connector;

import com.aquariux.platform.trading.infra.connector.model.BinanceBookTickersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "binance-service", url = "https://api.binance.com")
public interface BinanceConnector {
    @GetMapping("/api/v3/ticker/bookTicker")
    BinanceBookTickersResponse getPrices();
}
