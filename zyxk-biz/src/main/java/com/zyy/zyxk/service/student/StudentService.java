package com.zyy.zyxk.service.student;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.dao.entity.Student;

public interface StudentService extends IService<Student> {
    //学生选择导师
    void teacherRel(String teacherId, UserJwtVo currentUser);
    //学生账号修改密码
    void ChangePassword(String oldPassword, String newPassword, UserJwtVo currentUser);
}
