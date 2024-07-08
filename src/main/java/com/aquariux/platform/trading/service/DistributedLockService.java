package com.aquariux.platform.trading.service;

import java.util.concurrent.TimeUnit;

public interface DistributedLockService extends AutoCloseable {
    AutoCloseable tryLock(String key, long expireTime, TimeUnit timeUnit);
    void unlock(String key);
}
