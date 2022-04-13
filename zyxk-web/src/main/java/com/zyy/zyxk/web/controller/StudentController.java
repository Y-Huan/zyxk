package com.zyy.zyxk.web.controller;


import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.student.StudentVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.student.StudentService;
import com.zyy.zyxk.service.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/student")
@Api(tags = "学生模块")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("teacherRel")
    @ApiOperation("学生选择导师接口")
    public Response teacherRel(String teacherId, HttpServletRequest request){
        UserJwtVo currentUser = JwtUtil.getCurrentUser(request.getHeader("token"));
        studentService.teacherRel(teacherId,currentUser);
        return Response.success("选择成功");
    }

    @GetMapping("list")
    @ApiOperation("学生列表")
    public Response studentList(String claseId,HttpServletRequest request){
        UserJwtVo currentUser = JwtUtil.getCurrentUser(request.getHeader("token"));
        return studentService.studentList(claseId,currentUser);
    }

    @PostMapping("add")
    @ApiOperation("新增学生")
    public Response addStudent(@RequestBody StudentVo studentVo , HttpServletRequest request){
        UserJwtVo currentUser = JwtUtil.getCurrentUser(request.getHeader("token"));
        if(StringUtils.isEmpty(studentVo.getStudentName())){
            return Response.fail(ErrorCode.NO_USERNAME);
        }
        return studentService.addStudent(studentVo,currentUser);
    }

    @GetMapping("creatorNumber")
    @ApiOperation("创建学号")
    public Response creatorNumber(){
        return studentService.creatorNumber();
    }

    @PostMapping("del")
    @ApiOperation("删除学生")
    public Response delStudent(@RequestBody StudentVo studentVo,HttpServletRequest request){
        String studentId = studentVo.getStudentId();
        UserJwtVo currentUser = JwtUtil.getCurrentUser(request.getHeader("token"));
        if(StringUtils.isEmpty(studentId)){
            return Response.fail(ErrorCode.BIND_ERROR);
        }
        return studentService.delStudent(studentId,currentUser);
    }
}
