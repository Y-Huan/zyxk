package com.zyy.zyxk.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @description 免认证注解
 * @author Yang.H
 * @date 2021/6/18
 * 
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FreeAuthentication {
    boolean required() default true;
}
