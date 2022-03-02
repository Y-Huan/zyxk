package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyy.zyxk.api.vo.LoginVo;
import com.zyy.zyxk.api.vo.TeacherVo;
import com.zyy.zyxk.dao.entity.Teacher;
import org.apache.ibatis.annotations.Param;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 4:46 PM
 */
public interface TeacherMapper extends BaseMapper<Teacher> {
    LoginVo getLogin(@Param("userName") String userName);

    IPage<TeacherVo> getList(IPage<TeacherVo> page,
                             @Param("selectStringKey") String selectStringKey,
                             @Param("schoolId") String schoolId);
}
