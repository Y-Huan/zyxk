package com.zyy.zyxk.service.student.impl;

import com.alibaba.fastjson.JSON;
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
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void teacherRel(String teacherId, UserJwtVo currentUser) {
       ListOperations<String ,String> test =(ListOperations<String ,String> )redisTemplate.opsForList();
       //此处应该将导师ID当作KEY
        if(test.size(JSON.toJSONString(teacherId))==0){
            System.out.println("当前令牌已为空");
        }else {
            System.out.println(test.leftPop(JSON.toJSONString(teacherId) ).toString());
        }

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
