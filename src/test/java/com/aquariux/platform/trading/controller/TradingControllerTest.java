package com.aquariux.platform.trading.controller;

import com.aquariux.platform.trading.service.AggregatedPriceService;
import com.aquariux.platform.trading.service.IdempotencyService;
import com.aquariux.platform.trading.service.TradingService;
import com.aquariux.platform.trading.service.domain.AggregatedPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TradingController.class)
public class TradingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AggregatedPriceService aggregatedPriceService;
    @MockBean
    private TradingService tradingService;
    @MockBean
    private IdempotencyService idempotencyService;

    @Test
    void shouldReturnBestPrice() throws Exception {
        AggregatedPrice price = new AggregatedPrice();
        mockMvc.perform(get("/trading/prices/best")
                        .param("symbol", "BTCUSDT")
                        .param("tradeType", "LONG")
                        .param("price", "1")
                        .param("quantity", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldThrowException() throws Exception {
        mockMvc.perform(get("/trading/prices/best")
                        .param("symbol", "ABC")
                        .param("tradeType", "BUY"))
                .andExpect(status().is4xxClientError());
    }
}
