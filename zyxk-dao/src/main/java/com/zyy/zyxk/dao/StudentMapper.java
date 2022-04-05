package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyy.zyxk.api.vo.LoginVo;
import com.zyy.zyxk.api.vo.student.StudentVo;
import com.zyy.zyxk.dao.entity.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 4:46 PM
 */
public interface StudentMapper extends BaseMapper<Student> {
    LoginVo getLogin(@Param("userName") String userName);

    StudentVo selectStudent(@Param("studentId") String id);
}
