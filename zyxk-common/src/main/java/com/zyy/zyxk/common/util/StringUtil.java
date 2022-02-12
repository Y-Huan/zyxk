package com.zyy.zyxk.common.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang.RandomStringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
  *
  * @desc string 工具类
  * @author Yang.H
  * @date 2021/8/19
  *
  **/
@Slf4j
public class StringUtil {
    public static String object2json(Object object){
        return JSON.toJSONString(object);
    }

    public static <T> T json2object(String json,Class<T> clazz){
        return JSON.parseObject(json,clazz);
    }

    public static String object2jsonBase64(Object object){
        return base64Encoder(object2json(object));
    }

    public static <T> T json2base64object(String json,Class<T> clazz){
        return json2object(base64Decoder(json),clazz);
    }
    /**
     * 字符串转换成为16进制(无需Unicode编码)
     * @param str
     * @return
     */
    public static String string2Hex(String str) {
        char[] chars = "0123456789abcdef".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 16进制直接转换成为字符串(无需Unicode解码)
     * @param hexStr
     * @return
     */
    public static String hex2String(String hexStr) {
        String str = "0123456789abcdef";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    public static String urlEncode(String str){
        try {
            return URLEncoder.encode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String urlDecode(String encodedString){
        try {
            return URLDecoder.decode(encodedString,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encodedString;
    }

    public static String encrypt(String source){
        return string2Hex(urlEncode(source));
    }

    public static String decrypt(String encryptedStr){
        return urlDecode(hex2String(encryptedStr));
    }

    public static String generateAutoLoginToken(String username,String password){
        return String.format("%s-%s",base64Encoder(username),base64Encoder(password));
    }

    public static String generateUniqueCode(){
        String code= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"))+generateRandomNumber(5);
        return code;
    }

    public static String generateRandomNumber(int bits){
        return RandomStringUtils.random(bits, false, true);
    }

    /**
     * BASE64加密
     * @param src
     * @return
     */
    public static String base64Encoder(String src) {
        String encoding="UTF-8";
        String ciphertext = null;

        try {
            BASE64Encoder b64encoder = new BASE64Encoder();
            ciphertext = b64encoder.encode(src.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return ciphertext;
    }

    /**
     * BASE64解密
     * @param dest
     * @return
     **/
    public static String base64Decoder(String dest) {
        String encoding="UTF-8";
        byte[] b = null;

        try {
            BASE64Decoder b64decoder = new BASE64Decoder();
            b = b64decoder.decodeBuffer(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String plaintext = null;
        try {
            plaintext = new String(b, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return plaintext;
    }

    public static void main(String[] args) {
//        String username="15068172903";
//        String password="123456";
        String n=generateRandomNumber(5);
        String token=generateUniqueCode();
//        if(!StringUtils.isNullOrEmpty(token)){
//            String[] tokens=token.split("-");
//            username= StringUtil.base64Decoder(tokens[0]);
//            password= StringUtil.base64Decoder(tokens[1]);
//        }
        System.out.println("token: "+token);
    }
}
