package com.zyy.zyxk.service;

public interface RedisService {
    void setToken(String userId, String token);

    String getToken(String userId);

    boolean deleteToken(String userId);

    boolean isValidToken(String userId);

    //测试令牌桶
    void test(String id, Integer x);

    boolean testget( String id);
    //获取令牌桶的剩余令牌数
    Integer getListSize(String tutorId);
}
