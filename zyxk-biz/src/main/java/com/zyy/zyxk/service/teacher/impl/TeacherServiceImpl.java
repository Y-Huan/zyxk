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
import com.zyy.zyxk.dao.TeacherRoleRelMapper;
import com.zyy.zyxk.dao.entity.Teacher;
import com.zyy.zyxk.dao.entity.TeacherRoleRel;
import com.zyy.zyxk.service.RedisService;
import com.zyy.zyxk.service.common.CommonService;
import com.zyy.zyxk.service.teacher.TeacherService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherRoleRelMapper teacherRoleRelMapper;

    @Autowired
    private CommonService commonService;

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
        teacher.setTeacherId(commonService.getSequence("TEACHER",null));
        teacher.setSalt( EncryptUtil.salt());
        teacher.setPassword(EncryptUtil.encryptPassword(teacher.getPhone(), teacher.getSalt()));
        teacher.setCreateTime(LocalDateTime.now());
        teacher.setCreator(currentUser.getId());
        teacherMapper.insert(teacher);
        if(StringUtils.isNotEmpty(teacher.getRoleId())) {
            String[] roleIds = teacher.getRoleId().split(",");
            for (String roleId:roleIds) {
                TeacherRoleRel teacherRoleRel = new TeacherRoleRel();
                teacherRoleRel.setTeacherId(teacher.getTeacherId());
                teacherRoleRel.setRoleId(roleId);
                teacherRoleRel.setCreator(currentUser.getId());
                teacherRoleRel.setCreateTime(LocalDateTime.now());
                teacherRoleRelMapper.insert(teacherRoleRel);
            }
        }
    }

    @Override
    @Transactional
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

    @Override
    public Teacher getDetail(String teacherId) {
        return teacherMapper.selectById(teacherId);
    }

    @Override
    @Transactional
    public void resetPwd(String teacherId) {
        //查出教师信息
        Teacher teacher = teacherMapper.selectById(teacherId);
        //判空
        if (teacher == null){
            throw new BizException(ErrorCode.USER_NOT_EXISTS);
        }
        //将密码设为手机号重新获取盐
        teacher.setSalt(EncryptUtil.salt());
        teacher.setPassword(EncryptUtil.encryptPassword(teacher.getPhone(),teacher.getSalt()));
        teacherMapper.updateById(teacher);
        //将该账号正在登录的设备踢出
        redisService.deleteToken(teacherId);
    }

    @Override
    @Transactional
    public void delect(String teacherId) {
        Teacher teacher = teacherMapper.selectById(teacherId);
        if(teacher == null){
            throw new BizException(ErrorCode.USER_NOT_EXISTS);
        }
        teacher.setIsDel(false);
        teacher.setUpdateTime(LocalDateTime.now());
        teacherMapper.updateById(teacher);
    }

    @Override
    @Transactional
    public void updateTeacher(TeacherVo teacherVo, UserJwtVo currentUser) {
        //判断该教师是否存在有效
        Teacher teacher = teacherMapper.selectById(teacherVo.getTeacherId());
        if(teacher==null){
            throw new BizException(ErrorCode.USER_NOT_EXISTS);
        }
        if(!teacher.getIsDel()){
            throw new BizException(ErrorCode.ACCOUNT_STOP_USING);
        }
        //判断手机号是否有重复
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",teacherVo.getPhone());
        queryWrapper.ne("teacher_id",teacherVo.getTeacherId());
        queryWrapper.eq("is_del",true);
        Teacher selectOne = teacherMapper.selectOne(queryWrapper);
        if(selectOne != null){
            throw new BizException(ErrorCode.USER_EXISTED);
        }
        BeanUtil.copyProperties(teacherVo,teacher);
        teacher.setUpdateTime(LocalDateTime.now());
        teacherMapper.updateById(teacher);
        QueryWrapper<TeacherRoleRel> teacherRoleRelQueryWrapper = new QueryWrapper<>();
        teacherRoleRelQueryWrapper.eq("teacher_id",teacher.getTeacherId());
        teacherRoleRelQueryWrapper.eq("is_del",true);
        List<TeacherRoleRel> teacherRoleRels = teacherRoleRelMapper.selectList(teacherRoleRelQueryWrapper);
        for (TeacherRoleRel teacherRoleRel : teacherRoleRels) {
            teacherRoleRel.setIsDel(false);
            teacherRoleRel.setUpdateTime(LocalDateTime.now());
            teacherRoleRelMapper.updateById(teacherRoleRel);
        }
        if(StringUtils.isNotEmpty(teacher.getRoleId())) {
            String[] roleIds = teacher.getRoleId().split(",");
            for (String roleId : roleIds) {
                TeacherRoleRel teacherRoleRel = new TeacherRoleRel();
                teacherRoleRel.setTeacherId(teacher.getTeacherId());
                teacherRoleRel.setRoleId(roleId);
                teacherRoleRel.setCreator(currentUser.getId());
                teacherRoleRel.setCreateTime(LocalDateTime.now());
                teacherRoleRelMapper.insert(teacherRoleRel);
            }
        }

    }
}
