package com.zyy.zyxk.service;

import com.zyy.zyxk.api.vo.LoginVo;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/14/22 8:46 AM
 */
public interface LoginService {
    //教师登录
    LoginVo teacherLogin(String userName, String password);
    //学生登录
    LoginVo studentLogin(String userName, String password);
}
