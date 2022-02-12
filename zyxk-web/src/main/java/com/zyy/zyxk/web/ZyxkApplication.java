package com.zyy.zyxk.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @Description zyxk项目启动类
 * @Author Yang.H
 * @Date 2022/1/25
 *
 **/
@SpringBootApplication
@ComponentScan({"com.zyy.zyxk.api", "com.zyy.zyxk.service", "com.zyy.zyxk.web", "com.zyy.zyxk.dao"})
@MapperScan("com.zyy.zyxk.dao")
public class ZyxkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZyxkApplication.class, args);
        System.out.println("...... zyy-zyxk start success ......................");
    }

}