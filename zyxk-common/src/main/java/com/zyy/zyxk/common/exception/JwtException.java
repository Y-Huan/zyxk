package com.zyy.zyxk.common.exception;

/**
 *
 * @Description jwt相关异常
 * @Author Yang.H
 * @Date 2021/6/19
 *
 **/
public class JwtException extends BaseException {

    private static final long serialVersionUID = 1L;

    public JwtException(String message) {
        super(message);
    }
}
