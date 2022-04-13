package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyy.zyxk.api.vo.course.CourseVo;
import com.zyy.zyxk.api.vo.course.UpdateCourseVo;
import com.zyy.zyxk.dao.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 4:42 PM
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 课程列表
     * @return
     */
    IPage<CourseVo> selectCourseList(IPage<CourseVo> page, @Param("courseName") String courseName, @Param("schoolId") String schoolId);

    /**
     * 课程详情
     * @param courseId
     * @return
     */
    CourseVo selectCourseById(@Param("courseId")String courseId);

    /**
     * 修改课程
     * @param updateCourseVo
     * @return
     */
    int updateCourseById(UpdateCourseVo updateCourseVo);

    String selectCourseDel(@Param("courseId") String courseId,
                           @Param("startTime") LocalDateTime now,
                           @Param("endTime") LocalDateTime plusDays);

    List<CourseVo> getStudentCourseList(@Param("stateTime") LocalDateTime stateTime,
                                        @Param("endTime") LocalDateTime endTime,
                                        @Param("schoolId") String schoolId,
                                        @Param("majorId")String majorId);

    List<CourseVo> getPublicStudentCourseList(@Param("stateTime") LocalDateTime stateTime,
                                              @Param("endTime") LocalDateTime endTime,
                                              @Param("schoolId") String schoolId);
}
