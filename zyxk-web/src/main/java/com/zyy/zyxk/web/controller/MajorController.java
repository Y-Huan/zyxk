package com.zyy.zyxk.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.major.InsertMajorVo;
import com.zyy.zyxk.api.vo.major.MajorListVo;
import com.zyy.zyxk.api.vo.major.SelectMajorVo;
import com.zyy.zyxk.api.vo.major.UpdateMajorVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.major.MajorService;
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
 * @date 2022-02-15
 **/
@Slf4j
@RequestMapping("major")
@RestController
@Api(tags = "专业")
public class MajorController {

    @Resource
    private MajorService majorService;

    @GetMapping("list")
    @ApiOperation(value = "专业列表" , notes = "专业列表")
    public Response<IPage<MajorListVo>> selectMajorList( SelectMajorVo selectMajorVo){
        IPage<MajorListVo> page = new Page<>();
        PageUtil.setPage(selectMajorVo.getPageNo(), selectMajorVo.getPageSize(), page);
        IPage<MajorListVo> majorList = majorService.selectMajorList(page,selectMajorVo);
        return Response.success("获取成功",majorList);
    }

    @GetMapping("detail")
    @ApiOperation(value = "专业详情" , notes = "专业详情")
    public Response selectMajorById(String majorId){
        MajorListVo major = majorService.selectedMajorById(majorId);
        return Response.success("查询成功",major);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除专业" , notes = "删除专业")
    public Response deleteMajorById(@RequestBody InsertMajorVo insertMajorVo){
        if(StringUtils.isEmpty(insertMajorVo.getMajorId())){
            return Response.fail(ErrorCode.Major_Id_Invalid);
        }
        majorService.deleteMajor(insertMajorVo.getMajorId());
        return Response.success("更新成功");

    }

    @PostMapping("add")
    @ApiOperation(value = "增加专业",notes = "增加专业")
    public Response addMajor(@RequestBody InsertMajorVo insertMajorVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(insertMajorVo.getMajorName())){
            return Response.fail(ErrorCode.No_Major_Name);
        }
        majorService.addMajor(insertMajorVo,currentUser);
        return Response.success("更新成功");
    }

    @PostMapping("update")
    @ApiOperation(value = "修改专业",notes = "修改专业")
    public Response updateMajor(@RequestBody UpdateMajorVo updateMajorVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        majorService.updateMajor(updateMajorVo,currentUser);
        return Response.success("更新成功");
    }
}
