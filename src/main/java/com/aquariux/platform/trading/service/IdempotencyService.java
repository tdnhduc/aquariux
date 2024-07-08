package com.aquariux.platform.trading.service;


public interface IdempotencyService {
    boolean isIdempotent(String key);
    void setIdempotent(String key);
}
