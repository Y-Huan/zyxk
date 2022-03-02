package com.zyy.zyxk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyy.zyxk.api.vo.LoginVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        teacherQueryWrapper.eq("is_del",true);
        Teacher teacher =  teacherMapper.selectOne(teacherQueryWrapper);
        if (teacher == null) {
            throw new BizException(ErrorCode.USER_NOT_EXISTS);
        }

        if (!teacher.getPassword().equals(EncryptUtil.encryptPassword(password,teacher.getSalt()))) {
            throw new BizException(ErrorCode.ERROR_ACCOUNT);
        }
        //查询返回所需的信息
        LoginVo loginVo =  teacherMapper.getLogin(userName);
        UserJwtVo userJwtVo = new UserJwtVo();
        BeanUtil.copyProperties(loginVo, userJwtVo);
        //生成token
        String token = JwtUtil.generateToken(userJwtVo);
        //将token放入redis
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
            throw new BizException(ErrorCode.USER_NOT_EXISTS);
        }
        if (!student.getPassword().equals(EncryptUtil.encryptPassword(password,student.getSalt()))) {
            throw new BizException(ErrorCode.ERROR_ACCOUNT);
        }
        //查询返回所需的信息
        LoginVo loginVo = studentMapper.getLogin(userName);
        UserJwtVo userJwtVo = new UserJwtVo();
        BeanUtil.copyProperties(loginVo, userJwtVo);
        //生成token
        String token = JwtUtil.generateToken(userJwtVo);
        //将token放入redis
        redisService.setToken(userJwtVo.getId(), token);
        List<String> authority = new ArrayList<>();
        authority.add("student");
        loginVo.setAuthorityPermission(authority);
        loginVo.setToken(token);
        return loginVo;
    }



    @Override
    public void logout(UserJwtVo currentUser) {
        //将token放入redis
        redisService.deleteToken(currentUser.getId());
    }

    //导入教师信息
    @Override
    @Transactional
    public boolean teacherInfo(Workbook excelInfo, UserJwtVo currentUser) {
        Sheet sheet = excelInfo.getSheetAt(0); //默认取第一个sheet
        int rowsNum = sheet.getLastRowNum();//获取最后一行的行标
        for(int j=1; j<rowsNum+1;j++) { //第一行为表头，所以从第二行开始
            Row row = sheet.getRow(j);
            if (row != null) {
                //判断手机号是否重复
                QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
                teacherQueryWrapper.eq("phone",row.getCell(1).toString());
                teacherQueryWrapper.eq("is_del",true);
                Teacher teacher1 = teacherMapper.selectOne(teacherQueryWrapper);
                if(teacher1!=null){
                    throw new BizException(ErrorCode.USER_EXISTED);
                }

                //取出所需要的信息
                Teacher teacher = new Teacher();
                teacher.setTeacherName(row.getCell(0).toString());
                teacher.setPhone((row.getCell(1).toString()));
                teacher.setSex(sex(row.getCell(2).toString()));
                teacher.setSalt(EncryptUtil.salt());
                teacher.setPassword(EncryptUtil.encryptPassword(teacher.getPhone(), teacher.getSalt()));
                teacher.setSchoolId(currentUser.getSchoolId());
                teacher.setCreator(currentUser.getId());
                teacher.setCreateTime(LocalDateTime.now());
                teacher.setIsDel(true);
                teacherMapper.insert(teacher);
            }
        }
        return true;
    }

    //判断性别
    public Integer sex(String sex){
        if("男".equals(sex)){
            return 1;
        }else if("女".equals(sex)){
            return 2;
        }else {
            return 0;
        }
    }

    @Override
    public boolean studentInfo(Workbook excelInfo, UserJwtVo currentUser,String cleasId) {
        Sheet sheet = excelInfo.getSheetAt(0); //默认取第一个sheet
        int rowsNum = sheet.getLastRowNum();//获取最后一行的行标
        for(int j=1; j<rowsNum+1;j++) { //第一行为表头，所以从第二行开始
            Row row = sheet.getRow(j);
            if (row != null) {
                QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
                studentQueryWrapper.eq("student_number",row.getCell(1).toString());
                studentQueryWrapper.eq("is_valid",true);
                Student student1 = studentMapper.selectOne(studentQueryWrapper);
                if(student1!=null){
                    throw new BizException(ErrorCode.USER_EXISTED);
                }
                //取出所需要的信息
               Student student = new Student();
               student.setStudentNumber(row.getCell(0).toString());
               student.setStudentName(row.getCell(1).toString());
               student.setClaseId(cleasId);
               student.setSchoolId(currentUser.getSchoolId());
               student.setPhone(row.getCell(2).toString());
               student.setSex(sex(row.getCell(3).toString()));
               student.setSalt(EncryptUtil.salt());
               student.setPassword(EncryptUtil.encryptPassword(student.getPhone(),student.getSalt()));
               student.setCreator(currentUser.getId());
               student.setCreateTime(LocalDateTime.now());
               student.setIsDel(true);
               studentMapper.insert(student);
            }
        }
        return true;
    }
}
