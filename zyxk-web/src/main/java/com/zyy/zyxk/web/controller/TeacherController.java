package com.zyy.zyxk.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyy.zyxk.api.vo.TeacherVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.dao.entity.Teacher;
import com.zyy.zyxk.service.teacher.TeacherService;
import com.zyy.zyxk.service.util.JwtUtil;
import com.zyy.zyxk.service.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Api(tags = "教师模块")
@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("add")
    @ApiOperation("新增教师")
    public Response add(@RequestBody TeacherVo teacherVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(teacherVo.getTeacherName())){
            return Response.fail(ErrorCode.NO_USERNAME);
        }
        if(StringUtils.isEmpty(teacherVo.getPhone())){
            return Response.fail(ErrorCode.NO_USER_PHONE);
        }
        teacherService.add(teacherVo,currentUser);
        return Response.success("新增成功");
    }

    @PostMapping("update")
    @ApiOperation("编辑教师信息")
    public Response update(@RequestBody TeacherVo teacherVo,HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(teacherVo.getTeacherName())){
            return Response.fail(ErrorCode.NO_USERNAME);
        }
        if(StringUtils.isEmpty(teacherVo.getPhone())){
            return Response.fail(ErrorCode.NO_USER_PHONE);
        }
        teacherService.updateTeacher(teacherVo,currentUser);
        return Response.success();
    }

    @PostMapping("delete")
    @ApiOperation("删除教师信息")
    public Response delete(@RequestBody TeacherVo teacherVo,HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(teacherVo.getTeacherId())){
            return Response.fail(ErrorCode.NO_USER_ID);
        }
        if (currentUser.getId().equals(teacherVo.getTeacherId())){
            return Response.fail(ErrorCode.DELECT_ME_ERROR);
        }
        if("ZYXKTCR000000002".equals(teacherVo.getTeacherId())){
            return Response.fail(ErrorCode.AMDIN_DELETE_ERROR);
        }
        teacherService.delect(teacherVo.getTeacherId());
        return Response.success();
    }

    @PostMapping("resetPwd")
    @ApiOperation("重置教师账号密码")
    public Response resetPwd(@RequestBody TeacherVo teacher){
        if (StringUtils.isEmpty(teacher.getTeacherId())){
            return Response.fail(ErrorCode.NO_USER_ID);
        }
        teacherService.resetPwd(teacher.getTeacherId());
        return Response.success("更新成功");
    }

    @GetMapping("list")
    @ApiOperation("教师列表")
    public Response<IPage<TeacherVo>> list(BaseSelectVo baseSelectVo, HttpServletRequest request) {
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        IPage<TeacherVo> page = new Page<>();
        PageUtil.setPage(baseSelectVo.getPageNo(), baseSelectVo.getPageSize(), page);
        IPage<TeacherVo> teacherVoIPage = teacherService.getList(baseSelectVo,currentUser,page);
        return Response.success("获取成功",teacherVoIPage);
    }

    @GetMapping("detail")
    @ApiOperation("教师信息")
    public Response detail(String teacherId){
        if(StringUtils.isEmpty(teacherId)){
            return Response.fail(ErrorCode.NO_USER_ID);
        }
        Teacher teacher = teacherService.getDetail(teacherId);
        return Response.success("获取成功",teacher);
    }
}
