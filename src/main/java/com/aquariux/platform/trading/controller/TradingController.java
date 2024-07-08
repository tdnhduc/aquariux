package com.aquariux.platform.trading.controller;

import com.aquariux.platform.trading.constant.Constant;
import com.aquariux.platform.trading.controller.dto.OderRequest;
import com.aquariux.platform.trading.controller.dto.OrderHistory;
import com.aquariux.platform.trading.controller.dto.OrderHistoryResponse;
import com.aquariux.platform.trading.domain.SupportedSymbol;
import com.aquariux.platform.trading.domain.TradeType;
import com.aquariux.platform.trading.infra.exception.BusinessException;
import com.aquariux.platform.trading.service.AggregatedPriceService;
import com.aquariux.platform.trading.service.IdempotencyService;
import com.aquariux.platform.trading.service.TradingService;
import com.aquariux.platform.trading.service.domain.AggregatedPrice;
import com.aquariux.platform.trading.service.domain.OrderPlace;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trading")
@Log4j2
@AllArgsConstructor
public class TradingController {
    private AggregatedPriceService aggregatedPriceService;
    private TradingService tradingService;
    private IdempotencyService idempotencyService;

    @GetMapping(value = "/prices/best", produces = "application/json")
    AggregatedPrice getBestPrice(
            @RequestParam("symbol") SupportedSymbol symbol,
            @RequestParam("tradeType") TradeType tradeType,
            HttpServletRequest servletRequest) throws BusinessException {
        // no need check permission user
        return this.aggregatedPriceService.getBestPrice(symbol, tradeType);
    }

    @PostMapping(value = "/place-order", produces = "application/json")
    OrderPlace placeOrder(
            @RequestBody OderRequest oderRequest,
            HttpServletRequest servletRequest) throws Exception {
        var userId = servletRequest.getHeader(Constant.PROFILE_HEADER_KEY);
        this.checkPermission(userId);
        this.checkIdempotency(servletRequest.getHeader(Constant.IDEMPOTENCY_KEY_HEADER));

        return this.tradingService.placeOrder(userId, oderRequest.getSymbol(), oderRequest.getTradeType(),
                oderRequest.getPrice(), oderRequest.getQuantity());
    }

    @GetMapping(value = "/history", produces = "application/json")
    List<OrderHistoryResponse> getHistory(
            @RequestParam("symbol") SupportedSymbol symbol,
            @RequestParam(value = "limit", defaultValue = "50") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(value = "sort", defaultValue = "ASC") String sort,
            HttpServletRequest servletRequest) throws Exception {
        var userId = servletRequest.getHeader(Constant.PROFILE_HEADER_KEY);
        this.checkPermission(userId);
        var historyRequest = OrderHistory.builder()
                .userId(userId)
                .symbol(symbol)
                .limit(limit)
                .offset(offset)
                .from(LocalDateTime.from(from.atStartOfDay()))
                .sort(sort)
                .to(LocalDateTime.from(to.atStartOfDay()))
                .build();
        return this.tradingService.getHistory(historyRequest);
    }

    private void checkPermission(String profileId) {
        if (profileId.isBlank()) {
            throw new IllegalArgumentException("Missing profile ID");
        }
    }

    private void checkIdempotency(String idempotencyKey) {
        if (idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("Missing idempotency key");
        }
        if (this.idempotencyService.isIdempotent(idempotencyKey)) {
            return;
        }
        this.idempotencyService.setIdempotent(idempotencyKey);
    }
}
