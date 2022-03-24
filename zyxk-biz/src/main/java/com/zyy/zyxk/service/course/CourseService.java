package com.zyy.zyxk.service.course;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.course.CourseVo;
import com.zyy.zyxk.api.vo.course.InsertCourseVo;
import com.zyy.zyxk.api.vo.course.SelectCourseVo;
import com.zyy.zyxk.api.vo.course.UpdateCourseVo;
import com.zyy.zyxk.dao.entity.Course;
import org.apache.ibatis.annotations.Param;

/**
 * @author fl
 * @Date 2022-02-16
 */
public interface CourseService extends IService<Course> {
    /**
     * 课程列表
     * @param page
     * @param currentUser
     * @param selectCourseVo
     * @return
     */
    IPage<CourseVo> selectCourseList(IPage<CourseVo> page, SelectCourseVo selectCourseVo,UserJwtVo currentUser);

    /**
     * 课程详情
     * @param courseId
     * @return
     */
    CourseVo selectCourseById(@Param("courseId")String courseId);

    /**
     * 添加课程
     * @param insertCourseVo
     * @param currentUser
     */
    void addCourse(InsertCourseVo insertCourseVo, UserJwtVo currentUser);

    /**
     * 修改课程
     * @param updateCourseVo
     * @param currentUser
     */
    void updateCourse(UpdateCourseVo updateCourseVo ,UserJwtVo currentUser);

    /**
     * 删除课程
     * @param courseId
     */
    void delCourse(String courseId);
}
