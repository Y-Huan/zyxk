package com.zyy.zyxk.web.controller;

import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.course.CourseVo;
import com.zyy.zyxk.api.vo.course.InsertCourseVo;
import com.zyy.zyxk.api.vo.course.SelectCourseVo;
import com.zyy.zyxk.api.vo.course.UpdateCourseVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.course.CourseService;
import com.zyy.zyxk.service.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    @PostMapping("/list")
    public Response selectCourseList(@RequestBody SelectCourseVo selectCourseVo){
        List<CourseVo> courseVoList = new ArrayList<>();
        try {
            courseVoList = courseService.selectCourseList(selectCourseVo);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("获取成功",courseVoList);
    }

    @GetMapping("/{courseId}")
    @ApiOperation(value = "分院详情",notes = "分院详情")
    public Response selectCourseById(@PathVariable String courseId){
        CourseVo courseVo = courseService.selectCourseById(courseId);
        return Response.success("获取成功",courseVo);
    }

    @PostMapping("/insert")
    @ApiOperation(value = "增加课程",notes = "增加课程")
    public Response insertCourse(@RequestBody InsertCourseVo insertCourseVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(insertCourseVo.getCourseName())){
            return Response.fail(ErrorCode.No_Course_Name);
        }

        try {
            courseService.addCourse(insertCourseVo,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }
    @PutMapping("/update")
    @ApiOperation(value = "修改分院" , notes = "修改分院")
    public Response updateCollege(@RequestBody UpdateCourseVo updateCourseVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        try {
            courseService.updateCourse(updateCourseVo,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }

    @DeleteMapping("/{courseId}")
    @ApiOperation(value = "删除分院" , notes = "删除分院")
    public Response deleteCollege(@PathVariable String courseId){
        if(StringUtils.isEmpty(courseId)){
            return Response.fail(ErrorCode.Course_Id_Invalid);
        }
        try {
            courseService.delCourse(courseId);
        }catch (Exception e){
            log.info(e.getMessage());
        }

        return Response.success("更新成功");
    }
}
