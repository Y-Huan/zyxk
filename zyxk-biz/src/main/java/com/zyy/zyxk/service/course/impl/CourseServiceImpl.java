package com.zyy.zyxk.service.course.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.course.*;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.util.BeanUtil;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.dao.CourseMapper;
import com.zyy.zyxk.dao.CourseStudentRelMapper;
import com.zyy.zyxk.dao.MajorMapper;
import com.zyy.zyxk.dao.StudentMapper;
import com.zyy.zyxk.dao.entity.Course;
import com.zyy.zyxk.dao.entity.CourseStudentRel;
import com.zyy.zyxk.dao.entity.Major;
import com.zyy.zyxk.service.RedisService;
import com.zyy.zyxk.service.course.CourseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fl
 * @date 2022-02-28
 **/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseStudentRelMapper courseStudentRelMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    RedisService redisService;

    /**
     * 课程列表
     *
     * @param selectCourseVo
     * @return
     */
    @Override
    public IPage<CourseVo> selectCourseList(IPage<CourseVo> page, SelectCourseVo selectCourseVo, UserJwtVo currentUser){
        return courseMapper.selectCourseList(page,selectCourseVo.getCourseName(),currentUser.getSchoolId());
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
    @Transactional
    public void addCourse(InsertCourseVo insertCourseVo, UserJwtVo currentUser) {
        Major major = new Major();
        if(insertCourseVo.getCourseType() == 1) {
            QueryWrapper<Major> majorQueryWrapper = new QueryWrapper<>();
            majorQueryWrapper.eq("person_in_charge_id", currentUser.getId());
            majorQueryWrapper.eq("is_del", true);
            major = majorMapper.selectOne(majorQueryWrapper);

            if (major == null) {
                throw new BizException(ErrorCode.BIND_ERROR);
            }
        }
        if(checkCourseName(insertCourseVo.getCourseName(),major.getMajorId(),null)){
            throw new BizException(ErrorCode.COURSE_NAME_NOTNULL_ERROR);
        }
        Course course = new Course();
        BeanUtil.copyProperties(insertCourseVo,course);
        course.setCreateTime(LocalDateTime.now());
        course.setMajorId(major.getMajorId());
        course.setCreator(currentUser.getId());
        course.setAuditStatus(0);
        course.setSchoolId(currentUser.getSchoolId());
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
        if(checkCourseName(updateCourseVo.getCourseName(),updateCourseVo.getMajorId(),updateCourseVo.getCourseId())){
            throw new BizException(ErrorCode.COURSE_NAME_NOTNULL_ERROR);
        }
        updateCourseVo.setUpdateTime(LocalDateTime.now());
        courseMapper.updateCourseById(updateCourseVo);
    }


    public boolean checkCourseName(String courseName,String majorId,String courseId){
        //判断是否有同专业的重名选修课存在
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(courseId)){
            courseQueryWrapper.ne("course_id",courseName);
        }
        courseQueryWrapper.eq("course_address",courseName);
        courseQueryWrapper.eq("major_id",majorId);
        courseQueryWrapper.eq("is_del",true);
        Course selectCourse = courseMapper.selectOne(courseQueryWrapper);
        if(selectCourse!=null){
            return  true;
        }
        return false;
    }

    public Course checkCourseDel(String courseId){
        //判断是否有该课程
        QueryWrapper<Course> courseWrapper = new QueryWrapper<>();
        courseWrapper.eq("course_id",courseId);
        courseWrapper.eq("is_del",true);
        Course course= courseMapper.selectOne(courseWrapper);
        if(course == null){
            return null;
        }
        return course;
    }

    /**
     * 删除课程
     *
     * @param courseId
     */
    @Override
    public void delCourse(String courseId) {
        Course course = checkCourseDel(courseId);
        //判断该课程是否已被学生选择
        QueryWrapper<CourseStudentRel> courseStudentRelQueryWrapper = new QueryWrapper<>();
        courseStudentRelQueryWrapper.eq("course_id",courseId);
        courseStudentRelQueryWrapper.eq("is_del",true);
        List<CourseStudentRel> selectList = courseStudentRelMapper.selectList(courseStudentRelQueryWrapper);
        if(selectList != null){
            throw new BizException(ErrorCode.COURSE_STUDENT_NOTNULL_ERROR);
        }
        course.setIsDel(false);
        course.setUpdateTime(LocalDateTime.now());
        courseMapper.updateById(course);
    }

    @Override
    public Response checkCourse(ChekcCourseVo chekcCourseVo, UserJwtVo currentUser) {
        Course course = checkCourseDel(chekcCourseVo.getCourseId());
        course.setAuditStatus(chekcCourseVo.getAuditStatus());
        if(chekcCourseVo.getAuditStatus() == 1){
            course.setCheckUser(currentUser.getId());
            redisService.test(chekcCourseVo.getCourseId(),course.getChoiceAmount());
        }
       courseMapper.updateById(course);

        return Response.success("更新成功");
    }

    @Override
    public Response choiceCourse(ChoiceCourseVo choiceCourseVo, UserJwtVo currentUser) {
        String selectone = courseMapper.selectCourseDel(choiceCourseVo.getCourseId(),LocalDateTime.now().minusDays(3),LocalDateTime.now());
        if (StringUtils.isEmpty(selectone)){
            return Response.fail(ErrorCode.COURSE_CHOICE_ERROR);
        }
        //判断该学生是否已选择课程
        String selectcheck = courseStudentRelMapper.selectChoice(choiceCourseVo.getStudentId(),LocalDateTime.now().minusDays(3),LocalDateTime.now());
        if (StringUtils.isNotEmpty(selectcheck)){
            return Response.fail(ErrorCode.STUDETN_HAVE_COURSE_ERROR);
        }
        if(!redisService.testget(choiceCourseVo.getCourseId())){
            return Response.fail(ErrorCode.COURSE_STUDENT_MAX_ERROR);
        }
        CourseStudentRel courseStudentRel = new CourseStudentRel();
        courseStudentRel.setCourseId(choiceCourseVo.getCourseId());
        courseStudentRel.setStudentId(currentUser.getId());
        courseStudentRel.setCreator(currentUser.getId());
        courseStudentRel.setCreateTime(LocalDateTime.now());
        courseStudentRelMapper.insert(courseStudentRel);
        return Response.success("选择成功");
    }

    @Override
    public Response getStudentCourseList(UserJwtVo currentUser) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime stateTime = LocalDateTime.now().minusDays(3);
        String majorId = studentMapper.getStudentMajor(currentUser.getId());
        List<CourseVo> courseVos = courseMapper.getStudentCourseList(stateTime,endTime,currentUser.getSchoolId(),majorId);
        List<CourseVo> courseVos1 = courseMapper.getPublicStudentCourseList(stateTime,endTime,currentUser.getSchoolId());
        for(CourseVo course1:courseVos1){
            courseVos.add(course1);
        }
        for(CourseVo course:courseVos){
            course.setChoiceAmount(redisService.getListSize(course.getCourseId()));
        }
        return Response.success("获取成功",courseVos);
    }
}
