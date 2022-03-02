package com.zyy.zyxk.web.controller;

import com.zyy.zyxk.api.vo.LoginInfoVo;
import com.zyy.zyxk.api.vo.LoginVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.common.annotation.FreeAuthentication;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.LoginService;
import com.zyy.zyxk.service.RedisService;
import com.zyy.zyxk.service.student.StudentService;
import com.zyy.zyxk.service.teacher.TeacherService;
import com.zyy.zyxk.service.util.ExcelUtil;
import com.zyy.zyxk.service.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    @Autowired
    private RedisService redisService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;


    @GetMapping("test")
    @FreeAuthentication
    @ApiOperation("测试令牌桶")
    public Response test(String id,Integer x){
        redisService.test(id,x);
        return Response.success();
    }

    @GetMapping("testget")
    @FreeAuthentication
    @ApiOperation("测试取令牌")
    public Response testget(String id,Integer x){

         redisService.testget(id);
        return Response.success();
    }


    @GetMapping("info")
    @FreeAuthentication
    @ApiOperation("用户登录")
    public Response info(LoginInfoVo loginInfoVo){
        if(StringUtils.isEmpty(loginInfoVo.getUsername())){
            return Response.fail(ErrorCode.NO_USERNAME);
        }
        if(StringUtils.isEmpty(loginInfoVo.getPassword())){
            return Response.fail(ErrorCode.NO_PASSWORD);
        }
        LoginVo loginVo = new LoginVo();
            //如果Type为1 则代表是老师登录如果为2则代表为学生登录
            if (loginInfoVo.getType() == 1){
                loginVo = loginService.teacherLogin(loginInfoVo.getUsername(),loginInfoVo.getPassword());
            }else if(loginInfoVo.getType() == 2){
                loginVo = loginService.studentLogin(loginInfoVo.getUsername(),loginInfoVo.getPassword());
            }else {
                return Response.fail(ErrorCode.BIND_ERROR);
            }
        return Response.success("登录成功",loginVo);
    }

    @GetMapping("logout")
    @ApiOperation("退出登录")
    public Response logout(HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        loginService.logout(currentUser);
        return Response.success();
    }

    @PutMapping("ChangePassword")
    @ApiOperation("修改密码")
    public Response ChangePassword (String oldPassword,String newPassword,HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if (StringUtils.isEmpty(oldPassword)){
            return Response.fail(ErrorCode.NO_OLD_PASSWORD);
        }
        if (StringUtils.isEmpty(newPassword)){
            return Response.fail(ErrorCode.NO_NEW_PASSWORD);
        }
        //如果中间三位为tcr则是教师ID则去教师service中处理
        if("TCR".equals(currentUser.getId().substring(4,7))){
            teacherService.ChangePassword(oldPassword,newPassword,currentUser);
        }else if("SDT".equals(currentUser.getId().substring(4,7))){
            studentService.ChangePassword(oldPassword,newPassword,currentUser);
        }else {
            return Response.fail(ErrorCode.BIND_ERROR);
        }
        return Response.success("更新成功");
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

    @PostMapping("studentInfo")
    @ApiOperation("学生信息导入")
    public Response studentInfo (@RequestParam("file") MultipartFile file,String cleasId, HttpServletRequest request) throws IOException {
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(!loginService.studentInfo(ExcelUtil.getExcelInfo(file),currentUser,cleasId)){
            return Response.fail(ErrorCode.BAD_PARAM);
        }
        return Response.success("导入成功");
    }



}
