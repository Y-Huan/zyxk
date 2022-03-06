package com.zyy.zyxk.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.school.SchoolVo;
import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.school.SchoolService;
import com.zyy.zyxk.service.util.JwtUtil;
import com.zyy.zyxk.service.util.PageUtil;
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
    public Response add(@RequestBody SchoolVo schoolVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(schoolVo.getSchoolName())){
            return Response.fail(ErrorCode.SCHOOL_NAME_NULL_ERROR);
        }
        schoolService.add(schoolVo,currentUser);
        return Response.success("更新成功");
    }

    @PostMapping("delSchool")
    @ApiOperation(value = "删除学校",notes = "删除学校")
    public Response delSchool(@RequestBody SchoolVo schoolVo,HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        schoolService.delSchool(schoolVo.getSchoolId(),currentUser);
        return Response.success("更新成功");
    }

    @PostMapping("update")
    @ApiOperation("编辑学校")
    public Response update(@RequestBody SchoolVo schoolVo,HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(schoolVo.getSchoolName())){
            return Response.fail(ErrorCode.SCHOOL_NAME_NULL_ERROR);
        }
        schoolService.updateSchool(schoolVo);
        return Response.success("更新成功");
    }


    @GetMapping("list")
    @ApiOperation("学校列表")
    public Response<IPage<SchoolVo>> list(BaseSelectVo baseSelectVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        IPage<SchoolVo> page = new Page<>();
        PageUtil.setPage(baseSelectVo.getPageNo(), baseSelectVo.getPageSize(), page);
        IPage<SchoolVo> schoolVoIPage = schoolService.getList(page,baseSelectVo.getSelectStringKey(),currentUser);
        return Response.success("获取成功",schoolVoIPage);
    }

    @GetMapping("detail")
    @ApiOperation("学校信息")
    public Response detail( SchoolVo schoolVo){
        if (StringUtils.isEmpty(schoolVo.getSchoolId())){
            return Response.fail(ErrorCode.SCHOOL_NULL_ERROR);
        }
        SchoolVo schoolVo1 = schoolService.detail(schoolVo.getSchoolId());
        return Response.success("获取成功",schoolVo1);
    }
}
