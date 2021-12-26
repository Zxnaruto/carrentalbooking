package com.zhaixin.carrentalbooking.util;

import com.zhaixin.carrentalbooking.config.RedisLock;
import com.zhaixin.carrentalbooking.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.UUID;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/25 14:59
 */
@Slf4j
@Component
public class OrderNoGenerator {
    @Autowired
    private RedisLock redisLock;

    public  String getOrderNo() {
        String orderNo = null;
        String token = UUID.randomUUID().toString();
        try {
            //获取不到锁自旋
            while (StringUtils.isEmpty(orderNo)) {
                // 获取分布式锁
                if (StringUtils.isEmpty(redisLock.lock(RedisConstant.REDIS_LOCK_KEY,token,5))) {
                    orderNo = token.replace("-","");
                    return orderNo;
                }
                log.info("创建订单号获取不到分布式锁,在自旋...,getOrderNo={}", RedisConstant.REDIS_LOCK_KEY);
            }
        } finally {
            redisLock.unlock(RedisConstant.REDIS_LOCK_KEY, token);
        }
        return token;
    }
}