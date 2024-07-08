package com.aquariux.platform.trading.controller;

import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.domain.TradeType;
import com.aquariux.platform.trading.infra.exception.BusinessException;
import com.aquariux.platform.trading.service.AggregatedPriceService;
import com.aquariux.platform.trading.service.domain.AggregatedPrice;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trading")
@Log4j2
@AllArgsConstructor
public class TradingController {
    private AggregatedPriceService aggregatedPriceService;

    @GetMapping(value = "/prices/best", produces = "application/json")
    AggregatedPrice getBestPrice(
            @RequestParam("symbol") SupportedSymbol symbol,
            @RequestParam("tradeType") TradeType tradeType,
            HttpServletRequest servletRequest) throws BusinessException {
        // no need check permission user
        return this.aggregatedPriceService.getBestPrice(symbol, tradeType);
    }

    private void checkPermission(String profileId) {
        if (profileId.isBlank()) {
            throw new IllegalArgumentException("Missing profile ID");
        }
    }
}
