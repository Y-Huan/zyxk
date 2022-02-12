package com.zyy.zyxk.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zyy.zyxk.web.interceptor.JwtInterceptor;

/**
 *
 * @Description 拦截器配置
 * @Author Yang.H
 * @Date 2021/6/19
 *
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //拦截所有请求
        registry.addInterceptor(jwtInterceptor()).addPathPatterns("/**");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)   //预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
                .allowedHeaders("*");
    }
}
