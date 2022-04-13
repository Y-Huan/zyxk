package com.zyy.zyxk.service.impl;

import com.alibaba.fastjson.JSON;
import com.zyy.zyxk.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
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


    @Override
    public void test(String id, Integer x) {

        ListOperations<String ,String> test =(ListOperations<String ,String> )redisTemplate.opsForList();
        for (int i = 0; i < x; i++) {
            test.leftPush(JSON.toJSONString(id),JSON.toJSONString(String.valueOf(i)));
        }

    }

    @Override
    public boolean testget(String id) {
        ListOperations<String ,String> test =(ListOperations<String ,String> )redisTemplate.opsForList();
        if(test.size(JSON.toJSONString(id))== 0){
            return false;
        }else {
            test.leftPop(JSON.toJSONString(id));
            return true;
        }
    }

    @Override
    public Integer getListSize(String tutorId) {
        Integer size = redisTemplate.opsForList().size(tutorId).intValue();
        return size;
    }
}
