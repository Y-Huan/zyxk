package com.zyy.zyxk.service.student.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.util.EncryptUtil;
import com.zyy.zyxk.dao.StudentMapper;
import com.zyy.zyxk.dao.entity.Student;
import com.zyy.zyxk.service.RedisService;
import com.zyy.zyxk.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void teacherRel(String teacherId, UserJwtVo currentUser) {
//        List<String> tokens = redisService.getToken(teacherId+currentUser.getSchoolId());
//        if(tokens.size()<=0){
//            throw new BizException(ErrorCode.AUTHORITY_NAME_ERROR);
//        }

    }

    @Override
    public void ChangePassword(String oldPassword, String newPassword, UserJwtVo currentUser) {
        Student student = studentMapper.selectById(currentUser.getId());
        if(student == null){
            throw  new BizException(ErrorCode.USER_UPDATE_ERROR_NO_ACCOUNT);
        }
        if (!student.getPassword().equals(EncryptUtil.encryptPassword(oldPassword, student.getSalt()))){
            throw new BizException(ErrorCode.OLD_PASSWORD_ERROR);
        }
        student.setSalt(EncryptUtil.salt());
        student.setPassword(EncryptUtil.encryptPassword(newPassword, student.getSalt()));
        studentMapper.updateById(student);
        redisService.deleteToken(currentUser.getId());
    }
}
