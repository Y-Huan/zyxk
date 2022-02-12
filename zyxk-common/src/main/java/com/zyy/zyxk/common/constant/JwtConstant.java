package com.zyy.zyxk.common.constant;

/**
 *
 * @Description jwt相关常量
 * @Author Yang.H
 * @Date 2021/6/18
 *
 **/
public class JwtConstant {
    /**
     * jwt加密密钥
     */
    public static final String SECRET_KEY = "7786df7fc3a34e26a61c034d5ec8245d";

    /**
     * token过期时间，单位为ms
     */
    public static final long EXPIRED_TIME = 24 * 60 * 60 * 1000;//24小时
}
