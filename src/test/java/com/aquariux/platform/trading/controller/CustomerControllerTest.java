package com.aquariux.platform.trading.controller;

import com.aquariux.platform.trading.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WalletService walletService;

    @Test
    void shouldReturnWallet() throws Exception {
        mockMvc.perform(get("/customer/wallet")
                        .header("user-id", "123")
                        .param("currency", "USDT"))
                .andExpect(status().isOk());
    }
}
