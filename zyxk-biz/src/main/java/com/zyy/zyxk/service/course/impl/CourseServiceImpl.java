package com.zyy.zyxk.service.course.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.course.CourseVo;
import com.zyy.zyxk.api.vo.course.InsertCourseVo;
import com.zyy.zyxk.api.vo.course.SelectCourseVo;
import com.zyy.zyxk.api.vo.course.UpdateCourseVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.util.BeanUtil;
import com.zyy.zyxk.dao.CourseMapper;
import com.zyy.zyxk.dao.entity.Course;
import com.zyy.zyxk.service.course.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fl
 * @date 2022-02-28
 **/
@Service
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
        return courseMapper.selectCourseList(selectCourseVo);
    }

    /**
     * 课程详情
     *
     * @param courseId
     * @return
     */
    @Override
    public CourseVo selectCourseById(String courseId) {
        return courseMapper.selectCourseById(courseId);
    }

    /**
     * 添加课程
     *
     * @param insertCourseVo
     * @param currentUser
     */
    @Override
    public void addCourse(InsertCourseVo insertCourseVo, UserJwtVo currentUser) {
        Course course = new Course();
        BeanUtil.copyProperties(insertCourseVo,course);
        courseMapper.insert(course);
    }

    /**
     * 修改课程
     *
     * @param updateCourseVo
     * @param currentUser
     */
    @Override
    public void updateCourse(UpdateCourseVo updateCourseVo, UserJwtVo currentUser) {
        updateCourseVo.setCreator(currentUser.getUserName());
        updateCourseVo.setUpdateTime(LocalDateTime.now());
        courseMapper.updateCourseById(updateCourseVo);
    }

    /**
     * 删除课程
     *
     * @param courseId
     */
    @Override
    public void delCourse(String courseId) {
        QueryWrapper<Course> courseWrapper = new QueryWrapper<>();
        courseWrapper.eq("course_id",courseId);
        courseWrapper.eq("is_del",true);
        Course course= courseMapper.selectOne(courseWrapper);
        if(course == null){
            throw new BizException(ErrorCode.Course_Id_Invalid);
        }
        course.setIsDel(false);
        course.setUpdateTime(LocalDateTime.now());
        courseMapper.updateById(course);
    }
}
