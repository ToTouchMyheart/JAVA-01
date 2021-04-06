package io.zero.zerocache.service;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author zhurui
 * @Date 2021/4/6 1:36 下午
 * @Version 1.0
 */
public class Counter {
    @Autowired
    private RedissonClient redissonClient;

    public void set(String key, long count) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        atomicLong.set(count);
    }

    public boolean decrement(String key) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        return atomicLong.decrementAndGet() > 0;
    }
}
