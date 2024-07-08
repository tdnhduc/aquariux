package com.aquariux.platform.trading.service.impl;

import com.aquariux.platform.trading.service.DistributedLockService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DistributedLockServiceServiceImpl implements DistributedLockService {
    @Override
    public AutoCloseable tryLock(String key, long expireTime, TimeUnit timeUnit) {
        return  () -> {}; // NO-Ops
    }

    @Override
    public void unlock(String key) {
        // NO-Ops
    }

    @Override
    public void close() throws Exception {
        // NO-Ops
    }
}
