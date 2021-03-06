package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyy.zyxk.dao.entity.CourseStudentRel;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 4:43 PM
 */
public interface CourseStudentRelMapper extends BaseMapper<CourseStudentRel> {
    String selectChoice(@Param("studentId") String studentId,
                                  @Param("startTime") LocalDateTime now,
                                  @Param("endTime")LocalDateTime now2 );
}
