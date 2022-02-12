package com.zyy.zyxk.common.vo;

import com.zyy.zyxk.common.constant.ErrorCode;

import java.io.Serializable;

/**
 *
 * @Description 统一的响应结果bean
 * @Author Yang.H
 * @Date 2021/6/2
 *
 **/
public class Response<T> implements Serializable {
    private static final long serialVersionUID = -5609187686109612101L;
    /**
     * 请求响应编码
     */
    private int code;
    /**
     * 请求响应消息
     */
    private String message;
    /**
     * 请求响应实体数据
     */
    private T data;

    public static <T> Response<T> success() {
        return new Response<T>(ErrorCode.SUCCESS);
    }

    public static <T> Response<T> success(String message) {
        return new Response<T>(ErrorCode.SUCCESS.getCode(), message);

    }

    public static <T> Response<T> success(String message, T data) {
        return new Response<T>(ErrorCode.SUCCESS.getCode(), message).setData(data);
    }

//    public static <T> Response<T> fail() {
//        return new Response<T>(ErrorEnum.COMMON_ERROR);
//    }

    public static <T> Response<T> fail(ErrorCode errorCode) {
        return new Response<T>(errorCode);
    }

    public static <T> Response<T> fail(ErrorCode errorCode, String message) {
        return new Response<T>(errorCode.getCode(), message);
    }

    public Response(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public Response(int code,String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

}