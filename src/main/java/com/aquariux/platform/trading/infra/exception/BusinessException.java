package com.aquariux.platform.trading.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
