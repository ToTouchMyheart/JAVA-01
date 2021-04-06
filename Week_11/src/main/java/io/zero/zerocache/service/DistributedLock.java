package io.zero.zerocache.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author zhurui
 * @Date 2021/4/6 1:35 下午
 * @Version 1.0
 */
@Component
public class DistributedLock {
    @Autowired
    private RedissonClient redissonClient;

    public RLock getLock(String key) {
        return redissonClient.getLock(key);
    }
}
