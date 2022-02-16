package com.zyy.zyxk.web.controller;

import com.zyy.zyxk.api.vo.LoginVo;
import com.zyy.zyxk.common.annotation.FreeAuthentication;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 3:40 PM
 */
@Slf4j
@Api(tags = "登录模块")
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("info")
    @FreeAuthentication
    @ApiOperation("用户登录")
    public Response info(String userName,String password,Integer type){
        if(StringUtils.isEmpty(userName)){
            return Response.fail(ErrorCode.NO_USERNAME);
        }
        if(StringUtils.isEmpty(password)){
            return Response.fail(ErrorCode.NO_PASSWORD);
        }
        LoginVo loginVo = new LoginVo();
        try {
            //如果Type为1 则代表是老师登录如果为2则代表为学生登录
            if (type == 1){
                loginVo = loginService.teacherLogin(userName,password);
            }else if(type == 2){
                loginVo = loginService.studentLogin(userName,password);
            }else {
                return Response.fail(ErrorCode.BIND_ERROR);
            }
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("登录成功",loginVo);
    }

    //测试



}
