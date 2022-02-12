package com.zyy.zyxk.service.util;

import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.common.constant.JwtConstant;
import com.zyy.zyxk.common.exception.JwtException;
import com.zyy.zyxk.common.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @Description jwt工具类
 * @Author Yang.H
 * @Date 2021/6/18
 *
 **/
public class JwtUtil {
    /**
     * 生成密钥
     * @return SecretKey
     */
    private static SecretKey generateKey(){
        String stringKey = JwtConstant.SECRET_KEY;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

        return secretKey;
    }

    /**
     * 生成token
     * @param userJwtVo
     * @return String
     */
    public static String generateToken(UserJwtVo userJwtVo){
        try {
            // 设置签发算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            // 生成密钥
            SecretKey key = generateKey();

            // 设置私有声明
            Map<String, Object> claims = new HashMap<>();
            String user=StringUtil.object2jsonBase64(userJwtVo);
            claims.put("user", user);

            // 记录生成JWT的时间
            long nowMillis = System.currentTimeMillis();

            // 设置过期时间
            long expMillis = nowMillis + JwtConstant.EXPIRED_TIME;
            Date expTime = new Date(expMillis);

            // 创建 token 构建器实例
            JwtBuilder jwtBuilder = Jwts.builder()
                    // 设置自己的私有声明
                    .setClaims(claims)
                    // 设置该 token 的Id，用于防止 token 重复
                    .setId(UUID.randomUUID().toString())
                    // 设置过期时间
                    .setExpiration(expTime)
                    // 设置签发算法和密钥
                    .signWith(signatureAlgorithm, key);
            return jwtBuilder.compact();
        } catch (Exception e) {
            e.printStackTrace();
            throw new JwtException("token 创建失败");
        }
    }

    /**
     * 验证 token
     * @param token
     * @return Claims
     * @throws Exception
     */
    public static Claims verifyToken(String token){
        SecretKey key = generateKey();

        // 获取 token 中的声明部分
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

//    public static long getUserId(String token){
//        return Long.parseLong(verifyToken(token).get("userId").toString());
//    }
//
//    public static String getUsername(String token){
//        return verifyToken(token).get("username").toString();
//    }
//
//    public static String getRealName(String token){
//        return verifyToken(token).get("realName").toString();
//    }

    public static UserJwtVo getCurrentUser(String token){
        String user=verifyToken(token).get("user").toString();
        return StringUtil.json2base64object(user,UserJwtVo.class);
    }

    /**
     * 清空当前session
     * @param session
     *//*
    public static void deleteSession(HttpSession session) {
        if (session == null) {
            return;
        }
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            // 获取session的属性名称
            String name = attributeNames.nextElement();
            session.removeAttribute(name);
        }
    }*/
}
