package com.zyy.zyxk.service;

public interface RedisService {
    void setToken(String userId, String token);

    String getToken(String userId);

    boolean deleteToken(String userId);

    boolean isValidToken(String userId);



}
