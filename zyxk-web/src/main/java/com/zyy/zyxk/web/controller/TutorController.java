package com.zyy.zyxk.web.controller;

import com.zyy.zyxk.api.vo.Tutor.AuditTutorVo;
import com.zyy.zyxk.api.vo.Tutor.CheckAuditTutorVo;
import com.zyy.zyxk.api.vo.Tutor.SelectAuditTutor;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.tutor.TutorService;
import com.zyy.zyxk.service.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yang.H
 * @version 1.0
 * @date 4/3/22 9:19 PM
 */
@Slf4j
@Api(tags = "导师模块")
@RestController
@RequestMapping("tutor")
public class TutorController {

    @Autowired
    TutorService tutorService;

    @ApiOperation("提交审核毕业导师")
    @PostMapping("add")
    public Response addTutor(@RequestBody AuditTutorVo auditTutorVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if (auditTutorVo.getTutorVos() == null){
            return Response.fail(ErrorCode.TUTOR_NULL_ERROR);
        }
        return tutorService.addTutor(auditTutorVo,currentUser);
    }

    @ApiOperation("审核单列表")
    @GetMapping("list")
    public Response auditTutorList(SelectAuditTutor selectAuditTutor){
        return tutorService.getAuditTutorList(selectAuditTutor);
    }

    @ApiOperation("审核导师信息")
    @PostMapping("check")
    public Response checkAuditTutor(CheckAuditTutorVo checkAuditTutorVo,HttpServletRequest request){
        UserJwtVo currentUser = JwtUtil.getCurrentUser(request.getHeader("token"));
        if (StringUtils.isEmpty(checkAuditTutorVo.getAuditTutorId()) || checkAuditTutorVo.getAuditStatus() == null){
            return Response.fail(ErrorCode.BIND_ERROR);
        }
        return tutorService.checkAuditTutor(checkAuditTutorVo,currentUser);
    }

    @ApiOperation("学生选择导师列表")
    @GetMapping("studentList")
    public Response studentTutorList(HttpServletRequest request){
        UserJwtVo currentUser = JwtUtil.getCurrentUser(request.getHeader("token"));
        return tutorService.studentTutorList(currentUser);
    }

    @ApiOperation("学生选择导师")
    @PostMapping("chooseTutor")
    public Response chooseTutor(String tutorId,HttpServletRequest request){
        UserJwtVo currentUser = JwtUtil.getCurrentUser(request.getHeader("token"));
        return tutorService.chooseTutor(tutorId,currentUser);
    }

}
