package com.zyy.zyxk.service.course.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.course.CourseVo;
import com.zyy.zyxk.api.vo.course.InsertCourseVo;
import com.zyy.zyxk.api.vo.course.SelectCourseVo;
import com.zyy.zyxk.api.vo.course.UpdateCourseVo;
import com.zyy.zyxk.dao.CourseMapper;
import com.zyy.zyxk.dao.entity.Course;
import com.zyy.zyxk.service.course.CourseService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fl
 * @date 2022-02-28
 **/
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;


    /**
     * 课程列表
     *
     * @param selectCourseVo
     * @return
     */
    @Override
    public List<CourseVo> selectCourseList(SelectCourseVo selectCourseVo) {
        return null;
    }

    /**
     * 课程详情
     *
     * @param courseId
     * @return
     */
    @Override
    public CourseVo selectCourseById(String courseId) {
        return null;
    }

    /**
     * 添加课程
     *
     * @param insertCourseVo
     * @param currentUser
     */
    @Override
    public void addCourse(InsertCourseVo insertCourseVo, UserJwtVo currentUser) {

    }

    /**
     * 修改课程
     *
     * @param updateCourseVo
     * @param currentUser
     */
    @Override
    public void updateCourse(UpdateCourseVo updateCourseVo, UserJwtVo currentUser) {

    }

    /**
     * 删除课程
     *
     * @param courseId
     */
    @Override
    public void delCourse(String courseId) {

    }
}
