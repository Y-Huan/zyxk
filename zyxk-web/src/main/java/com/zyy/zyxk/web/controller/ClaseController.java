package com.zyy.zyxk.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.clase.ClaseListVo;
import com.zyy.zyxk.api.vo.clase.InsertClaseVo;
import com.zyy.zyxk.api.vo.clase.SelectClaseVo;
import com.zyy.zyxk.api.vo.clase.UpdateClaseVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.clase.ClaseService;
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
 * @author fl
 * @date 2022-02-21
 **/
@Slf4j
@RestController
@RequestMapping("/clase")
@Api(tags = "班级模块")
public class ClaseController {

    @Autowired
    ClaseService claseService;

    @ApiOperation(value = "班级列表" , notes = "班级列表")
    @PostMapping("list")
    public Response<IPage<ClaseListVo>> selectClaseList(@RequestBody SelectClaseVo selectClaseVo, HttpServletRequest request){
        UserJwtVo currentUser = JwtUtil.getCurrentUser( request.getHeader("token"));
        IPage<ClaseListVo> page = new Page<>();
        PageUtil.setPage(selectClaseVo.getPageNo(), selectClaseVo.getPageSize(), page);
        IPage<ClaseListVo> claseListVoList  = claseService.selectClaseList(page,selectClaseVo,currentUser);
        return Response.success("获取成功",claseListVoList);
    }

    @GetMapping("detail")
    @ApiOperation(value = "班级详情" , notes = "班级详情")
    public Response selectMajorById( String claseId){
        ClaseListVo clase = claseService.selectClaseById(claseId);
        return Response.success("查询成功",clase);
    }
    @DeleteMapping("del")
    @ApiOperation(value = "删除班级" , notes = "删除班级")
    public Response deleteClaseById( InsertClaseVo insertClaseVo){
        if(StringUtils.isEmpty(insertClaseVo.getClaseId())){
            return Response.fail(ErrorCode.Clase_Id_Invalid);
        }
        claseService.deleteClase(insertClaseVo.getClaseId());
        return Response.success("更新成功");

    }

    @PostMapping("/add")
    @ApiOperation(value = "增加班级",notes = "增加班级")
    public Response addClase(@RequestBody InsertClaseVo insertClaseVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(insertClaseVo.getClaseName())){
            return Response.fail(ErrorCode.No_Clase_Name);
        }
        claseService.addClase(insertClaseVo,currentUser);
        return Response.success("更新成功");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改班级",notes = "修改班级")
    public Response updateClase(@RequestBody UpdateClaseVo updateClaseVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        claseService.updateClase(updateClaseVo,currentUser);
        return Response.success("更新成功");
    }
}
