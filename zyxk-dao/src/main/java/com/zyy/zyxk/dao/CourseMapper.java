package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyy.zyxk.api.vo.course.CourseVo;
import com.zyy.zyxk.api.vo.course.SelectCourseVo;
import com.zyy.zyxk.dao.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 4:42 PM
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 课程列表
     * @param selectCourseVo
     * @return
     */
    List<CourseVo> selectCourseList(SelectCourseVo selectCourseVo);

    /**
     * 课程详情
     * @param courseId
     * @return
     */
    CourseVo selectCourseById(@Param("courseId")String courseId);
}
