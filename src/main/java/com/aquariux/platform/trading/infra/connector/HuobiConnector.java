package com.aquariux.platform.trading.infra.connector;

import com.aquariux.platform.trading.infra.connector.model.HuobiBookTickersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "huobi-service", url = "https://api.huobi.pro")
public interface HuobiConnector {
    @GetMapping(value = "/market/tickers", consumes = "application/json")
    HuobiBookTickersResponse getPrices();
}
