package com.zyy.zyxk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyy.zyxk.api.vo.LoginVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.common.exception.AuthenticationException;
import com.zyy.zyxk.common.util.BeanUtil;
import com.zyy.zyxk.common.util.EncryptUtil;
import com.zyy.zyxk.dao.StudentMapper;
import com.zyy.zyxk.dao.TeacherMapper;
import com.zyy.zyxk.dao.entity.Student;
import com.zyy.zyxk.dao.entity.Teacher;
import com.zyy.zyxk.service.LoginService;
import com.zyy.zyxk.service.RedisService;
import com.zyy.zyxk.service.util.JwtUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/14/22 8:48 AM
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public LoginVo teacherLogin(String userName, String password) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.eq("phone",userName);
        teacherQueryWrapper.eq("is_valid",true);
        Teacher teacher =  teacherMapper.selectOne(teacherQueryWrapper);
        if (teacher == null) {
            throw new AuthenticationException("登录失败：该手机号不存在");
        }

        if (!teacher.getPassword().equals(EncryptUtil.encryptPassword(password,teacher.getSalt()))) {
            throw new AuthenticationException("登录失败：密码错误");
        }
        LoginVo loginVo = teacherMapper.getLogin(userName);
        UserJwtVo userJwtVo = new UserJwtVo();
        BeanUtil.copyProperties(loginVo, userJwtVo);
        String token = JwtUtil.generateToken(userJwtVo);
        redisService.setToken(userJwtVo.getId(), token);
        loginVo.setToken(token);
        return loginVo;
    }

    @Override
    public LoginVo studentLogin(String userName, String password) {
        QueryWrapper<Student> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.eq("student_number",userName);
        teacherQueryWrapper.eq("is_valid",true);
        Student student =  studentMapper.selectOne(teacherQueryWrapper);
        if (student == null) {
            throw new AuthenticationException("登录失败：该手机号不存在");
        }

        if (!student.getPassword().equals(EncryptUtil.encryptPassword(password,student.getSalt()))) {
            throw new AuthenticationException("登录失败：密码错误");
        }
        return null;
    }

    //导入教师信息
    @Override
    public boolean teacherInfo(Workbook excelInfo, UserJwtVo currentUser) {

        Sheet sheet = excelInfo.getSheetAt(0); //默认取第一个sheet
        int rowsNum = sheet.getLastRowNum();//获取最后一行的行标
        for(int j=3; j<rowsNum+1;j++) { //第一，二行为表头，所以从第三行开始
            Row row = sheet.getRow(j);
            if (row != null) {
                //取出所需要的信息



            }

        }
        return true;
    }
}
