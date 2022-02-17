package com.zyy.zyxk.web.controller;

import com.zyy.zyxk.api.vo.LoginVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.common.annotation.FreeAuthentication;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.LoginService;
import com.zyy.zyxk.service.util.ExcelUtil;
import com.zyy.zyxk.service.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

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

    @PostMapping("teacherInfo")
    @ApiOperation("教师信息导入")
    public Response teacherInfo (@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(!loginService.teacherInfo(ExcelUtil.getExcelInfo(file),currentUser)){
            return Response.fail(ErrorCode.BAD_PARAM);
        }
        return Response.success("导入成功");
    }



}
