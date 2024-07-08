package com.aquariux.platform.trading.controller;

import com.aquariux.platform.trading.constant.Constant;
import com.aquariux.platform.trading.controller.dto.WalletResponse;
import com.aquariux.platform.trading.controller.mapper.WalletResponseMapper;
import com.aquariux.platform.trading.domain.Currency;
import com.aquariux.platform.trading.service.WalletService;
import com.aquariux.platform.trading.service.domain.Wallet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@Log4j2
@AllArgsConstructor
public class CustomerController {
    private WalletService walletService;

    @GetMapping("/wallet")
    WalletResponse getWallet(
            @RequestParam("currency") Currency currency,
            HttpServletRequest request) {
        var userId = request.getHeader(Constant.PROFILE_HEADER_KEY);
        this.checkPermission(userId);
        var wallet = this.walletService.getWalletByUserId(userId, currency);
        return WalletResponseMapper.INSTANCE.toResponse(wallet);
    }

    private void checkPermission(String userId) {
        if (userId.isBlank()) {
            throw new IllegalArgumentException("Missing user ID");
        }
    }
}
