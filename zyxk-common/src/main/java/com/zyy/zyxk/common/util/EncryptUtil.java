package com.zyy.zyxk.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 *
 * @Description 通用工具
 * @Author Yang.H
 * @Date 2021/6/21
 *
 **/
public class EncryptUtil {
    /**
     * 使用md5+盐值加密
     * @param password 需要加密的密码
     * @param salt 加密盐值
     * @return 返回加密后的密码
     */
    public static String encryptPassword(String password, String salt){
        return String.valueOf(new SimpleHash("MD5", password, salt, 1000));
    }

    /**
     * 使用md5加密
     * @param source 需要md5的字符串
     * @return 返回md5后的字符串
     */
    public static String md5(String source){
        return String.valueOf(new SimpleHash("MD5", source));
    }
}
