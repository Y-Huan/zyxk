package com.zyy.zyxk.web.controller;


import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.student.StudentService;
import com.zyy.zyxk.service.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        try {
            studentService.teacherRel(teacherId,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("选择成功");
    }
}
