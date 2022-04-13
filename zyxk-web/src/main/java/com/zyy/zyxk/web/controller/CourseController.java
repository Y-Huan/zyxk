package com.zyy.zyxk.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.course.*;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.course.CourseService;
import com.zyy.zyxk.service.util.JwtUtil;
import com.zyy.zyxk.service.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author fl
 * @date 2022-03-01
 **/
@RestController
@RequestMapping("/course")
@Slf4j
@Api(tags = "课程模块")
public class CourseController {
    @Resource
    private CourseService courseService;

    @ApiOperation(value = "课程列表" , notes = "课程列表")
    @GetMapping("/list")
    public Response<IPage<CourseVo>> selectCourseList( SelectCourseVo selectCourseVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        IPage<CourseVo> page = new Page<>();
        PageUtil.setPage(selectCourseVo.getPageNo(), selectCourseVo.getPageSize(), page);
        IPage<CourseVo> courseVoList = courseService.selectCourseList(page,selectCourseVo,currentUser);
        return Response.success("获取成功",courseVoList);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "课程详情",notes = "课程详情")
    public Response selectCourseById( String courseId){
        CourseVo courseVo = courseService.selectCourseById(courseId);
        return Response.success("获取成功",courseVo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "发布课程",notes = "发布课程")
    public Response insertCourse(@RequestBody InsertCourseVo insertCourseVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(insertCourseVo.getCourseName())  ||  StringUtils.isEmpty(insertCourseVo.getCourseAddress()) ||  insertCourseVo.getTeacherType()==null ||
           insertCourseVo.getCourseType() ==null ){
            return Response.fail(ErrorCode.COURSE_INFO_NULL_ERROR);
        }
        courseService.addCourse(insertCourseVo,currentUser);
        return Response.success("更新成功");
    }
    @PostMapping("/update")
    @ApiOperation(value = "修改课程" , notes = "修改课程")
    public Response updateCollege(@RequestBody UpdateCourseVo updateCourseVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);

        courseService.updateCourse(updateCourseVo,currentUser);
        return Response.success("更新成功");
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除分院" , notes = "删除分院")
    public Response deleteCollege(@RequestBody String courseId,HttpServletRequest request){
        if(StringUtils.isEmpty(courseId)){
            return Response.fail(ErrorCode.Course_Id_Invalid);
        }
        courseService.delCourse(courseId);
        return Response.success("更新成功");
    }

    @ApiOperation("审核课程")
    @PostMapping("/checkCourse")
    public Response checkCourse(@RequestBody ChekcCourseVo chekcCourseVo,HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(chekcCourseVo.getCourseId()) || chekcCourseVo.getAuditStatus()==null){
            return Response.fail(ErrorCode.BIND_ERROR);
        }
        return courseService.checkCourse(chekcCourseVo,currentUser);
    }


    @ApiOperation("学生课程列表")
    @GetMapping("/student/courseList")
    public Response studentCourseList(HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        return courseService.getStudentCourseList(currentUser);
    }

    @ApiOperation("选择课程")
    @PostMapping("/choice")
    public Response choiceCourse(@RequestBody ChoiceCourseVo choiceCourseVo,HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(choiceCourseVo.getCourseId())){
            return Response.fail(ErrorCode.BIND_ERROR);
        }
        return courseService.choiceCourse(choiceCourseVo,currentUser);
    }
}
