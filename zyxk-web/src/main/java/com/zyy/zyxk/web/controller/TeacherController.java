package com.zyy.zyxk.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyy.zyxk.api.vo.TeacherVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import com.zyy.zyxk.common.annotation.FreeAuthentication;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
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
    @FreeAuthentication
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

    @GetMapping("list")
    @ApiOperation("教师列表")
    public Response< IPage<TeacherVo>> list(BaseSelectVo baseSelectVo, HttpServletRequest request) {
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        IPage<TeacherVo> page = new Page<>();
        PageUtil.setPage(baseSelectVo.getPageNo(), baseSelectVo.getPageSize(), page);
        IPage<TeacherVo> teacherVoIPage = teacherService.getList(baseSelectVo,currentUser,page);
        return Response.success("获取成功",teacherVoIPage);
    }
}
