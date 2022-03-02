package com.zyy.zyxk.service.teacher.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.TeacherVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.util.BeanUtil;
import com.zyy.zyxk.common.util.EncryptUtil;
import com.zyy.zyxk.dao.TeacherMapper;
import com.zyy.zyxk.dao.entity.Teacher;
import com.zyy.zyxk.service.RedisService;
import com.zyy.zyxk.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private RedisService redisService;

    @Override
    @Transactional
    public void add(TeacherVo teacherVo, UserJwtVo currentUser) {
        //判断手机号是否有重复
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",teacherVo.getPhone());
        queryWrapper.eq("is_del",true);
        Teacher selectOne = teacherMapper.selectOne(queryWrapper);
        if(selectOne != null){
            throw new BizException(ErrorCode.USER_EXISTED);
        }
        Teacher teacher = new Teacher();
        BeanUtil.copyProperties(teacherVo,teacher);
        teacher.setSalt( EncryptUtil.salt());
        teacher.setPassword(EncryptUtil.encryptPassword(teacher.getPhone(), teacher.getSalt()));
        teacherMapper.insert(teacher);
        redisService.deleteToken(currentUser.getId());
    }

    @Override
    public void ChangePassword(String oldPassword, String newPassword, UserJwtVo currentUser) {
        Teacher teacher = teacherMapper.selectById(currentUser.getId());
        if (teacher == null){
            throw  new BizException(ErrorCode.USER_UPDATE_ERROR_NO_ACCOUNT);
        }
        if (!teacher.getPassword().equals(EncryptUtil.encryptPassword(oldPassword, teacher.getSalt()))){
            throw new BizException(ErrorCode.OLD_PASSWORD_ERROR);
        }
        teacher.setSalt(EncryptUtil.salt());
        teacher.setPassword(EncryptUtil.encryptPassword(newPassword, teacher.getSalt()));
        teacher.setCreateTime(LocalDateTime.now());
        teacherMapper.updateById(teacher);

    }

    @Override
    public IPage<TeacherVo> getList(BaseSelectVo baseSelectVo, UserJwtVo currentUser, IPage<TeacherVo> page) {
        //如果学校ID是这个则代表是超级管理员，就把学校ID设为空查询所有教师
        if("0000000000000000".equals(currentUser.getSchoolId())){
            currentUser.setSchoolId(null);
        }

        return teacherMapper.getList(page,baseSelectVo.getSelectStringKey(),currentUser.getSchoolId());
    }
}
