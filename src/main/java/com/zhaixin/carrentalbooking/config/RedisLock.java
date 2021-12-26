package com.zhaixin.carrentalbooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/25 13:08
 */
@Component
public class RedisLock {

    @Autowired
    @Qualifier("myRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;


    // 注意，lock和unlock要在业务代码中加try{}finally{},保证解锁
    public String lock(String lockKey, String token, Integer lockExpireTime) {
        boolean result = false;
        try {
            result = redisTemplate.opsForValue().setIfAbsent(lockKey, token, lockExpireTime, TimeUnit.SECONDS);
        }catch (Exception e){
            return null;
        }

        if (result) {
            return token;
        }
        return null;
    }

    public boolean unlock(String lockKey, String token) {
        if (StringUtils.isEmpty(lockKey) || StringUtils.isEmpty(token)) {
            return false;
        }
        if (token.equals(redisTemplate.opsForValue().get(lockKey))) {
            redisTemplate.delete(lockKey);
            return true;
        }
        return false;
    }
}