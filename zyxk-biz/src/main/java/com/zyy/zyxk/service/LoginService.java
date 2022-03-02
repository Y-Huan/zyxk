package com.zyy.zyxk.service;

import com.zyy.zyxk.api.vo.LoginVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import org.apache.poi.ss.usermodel.Workbook;

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
    //导入教师信息
    boolean teacherInfo(Workbook excelInfo, UserJwtVo currentUser);
    //导入学生信息
    boolean studentInfo(Workbook excelInfo, UserJwtVo currentUser,String cleasId);
    //退出登录
    void logout(UserJwtVo currentUser);
}
