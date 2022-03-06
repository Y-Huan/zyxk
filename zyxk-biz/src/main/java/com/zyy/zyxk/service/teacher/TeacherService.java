package com.zyy.zyxk.service.teacher;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.api.vo.TeacherVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import com.zyy.zyxk.dao.entity.Teacher;

public interface TeacherService extends IService<Teacher> {
    //新增教师
    void add(TeacherVo teacherVo, UserJwtVo currentUser);
    //修改教师账号的密码
    void ChangePassword(String oldPassword, String newPassword, UserJwtVo currentUser);
    //获取教师列表
    IPage<TeacherVo> getList(BaseSelectVo baseSelectVo, UserJwtVo currentUser, IPage<TeacherVo> page);
    //获取教师信息
    Teacher getDetail(String teacherId);
    //重置教师账号密码
    void resetPwd(String teacherId);
    //删除教师账号
    void delect(String teacherId);
    //编辑教师信息
    void updateTeacher(TeacherVo teacherVo, UserJwtVo currentUser);
}
