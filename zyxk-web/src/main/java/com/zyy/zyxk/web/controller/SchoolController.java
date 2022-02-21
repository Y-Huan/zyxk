package com.zyy.zyxk.web.controller;

import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.school.SchoolVo;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.school.SchoolService;
import com.zyy.zyxk.service.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/18/22 4:43 PM
 */
@Slf4j
@Api(tags = "学校模块")
@RestController
@RequestMapping("school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping("addSchool")
    @ApiOperation(value = "新增学校",notes = "新增学校")
    public Response add(SchoolVo schoolVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        try {
            schoolService.add(schoolVo,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }

    @PostMapping("delSchool")
    @ApiOperation(value = "删除学校",notes = "删除学校")
    public Response delSchool(String schoolId,HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        try {
            schoolService.delSchool(schoolId,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }
}
