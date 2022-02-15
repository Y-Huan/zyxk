package com.zyy.zyxk.web.controller;

import com.zyy.zyxk.common.annotation.FreeAuthentication;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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

        try {
            //如果Type为1 则代表是老师登录如果为2则代表为学生登录
            if (type == 1){

            }
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success();
    }


}
