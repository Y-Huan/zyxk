package com.zyy.zyxk.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.college.CollegeListVo;
import com.zyy.zyxk.api.vo.college.InsertCollegeVo;
import com.zyy.zyxk.api.vo.college.UpdateCollegeVo;
import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.college.CollegeService;
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
@RestController
@RequestMapping("/college")
@Api(tags = "分院模块")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @PostMapping("add")
    @ApiOperation("新增分院")
    public Response add(@RequestBody InsertCollegeVo insertCollegeVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(insertCollegeVo.getCollegeName())){
            return Response.fail(ErrorCode.No_College_Name);
        }
        collegeService.insertCollege(insertCollegeVo,currentUser);
        return Response.success();
    }

    @PostMapping("del")
    @ApiOperation("删除分院")
    public Response del(@RequestBody UpdateCollegeVo updateCollegeVo , HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(updateCollegeVo.getCollegeId())){
            return Response.fail(ErrorCode.College_Id_Invalid);
        }
        collegeService.deleteCollege(updateCollegeVo.getCollegeId());
        return Response.success("更新成功");
    }

    @PostMapping("update")
    @ApiOperation("编辑分院")
    public Response update(@RequestBody UpdateCollegeVo updateCollegeVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(updateCollegeVo.getCollegeId())){
            return Response.fail(ErrorCode.College_Id_Invalid);
        }
        if(StringUtils.isEmpty(updateCollegeVo.getCollegeName())){
            return Response.fail(ErrorCode.No_College_Name);
        }
        collegeService.updateCollege(updateCollegeVo,currentUser);
        return Response.success();
    }


    @GetMapping("list")
    @ApiOperation("分院列表")
    public Response<IPage<CollegeListVo>> list (BaseSelectVo baseSelectVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        IPage<CollegeListVo> page = new Page<>();
        PageUtil.setPage(baseSelectVo.getPageNo(), baseSelectVo.getPageSize(), page);
        IPage<CollegeListVo> collegeListVoIPage = collegeService.selectCollegeList(page,baseSelectVo,currentUser.getSchoolId());
        return Response.success("获取成功",collegeListVoIPage);
    }

    @GetMapping("detail")
    @ApiOperation("分院信息")
    public Response detail(String collegeId){
        if(StringUtils.isEmpty(collegeId)){
            return Response.fail(ErrorCode.College_Id_Invalid);
        }
        CollegeListVo collegeListVo = collegeService.selectCollegeById(collegeId);
        return Response.success("获取成功",collegeListVo);
    }
}
