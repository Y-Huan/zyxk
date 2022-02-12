package com.zyy.zyxk.service.impl;

import com.zyy.zyxk.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;




    @Override
    public void setToken(String userId, String token) {
        ValueOperations valueOperations=redisTemplate.opsForValue();
        valueOperations.set(userId,token,1, TimeUnit.DAYS);
    }

    @Override
    public String getToken(String userId) {
        ValueOperations valueOperations=redisTemplate.opsForValue();
        return valueOperations.get(userId).toString();
    }

    @Override
    public boolean deleteToken(String userId) {
        return redisTemplate.delete(userId);
    }

    @Override
    public boolean isValidToken(String userId) {
        return redisTemplate.hasKey(userId);
    }


}
