package com.aquariux.platform.trading.service.impl;

import com.aquariux.platform.trading.service.IdempotencyService;
import org.springframework.stereotype.Service;

@Service
public class IdempotencyServiceImpl implements IdempotencyService {
    public boolean isIdempotent(String key) {
        return false;
    }

    public void setIdempotent(String key) {
        // NO-Ops
    }

}
