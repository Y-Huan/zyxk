package com.zyy.zyxk.web.controller;

import com.zyy.zyxk.api.vo.RoleVo;
import com.zyy.zyxk.api.vo.major.InsertMajorVo;
import com.zyy.zyxk.api.vo.major.MajorListVo;
import com.zyy.zyxk.api.vo.major.SelectMajorVo;
import com.zyy.zyxk.api.vo.major.UpdateMajorVo;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.dao.entity.Major;
import com.zyy.zyxk.service.MajorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.DebugLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fl
 * @date 2022-02-15
 **/
@Slf4j
@RequestMapping("/major")
@RestController
@Api(tags = "专业")
public class MajorController {

    @Resource
    private MajorService majorService;

    @PostMapping("/list")
    @ApiOperation(value = "专业列表" , notes = "专业列表")
    public Response selectMajorList(@RequestBody SelectMajorVo selectMajorVo){
        List<MajorListVo> majorList = new ArrayList<>();
        try {
            majorList = majorService.selectMajorList(selectMajorVo);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("获取成功",majorList);
    }

    @GetMapping("/{majorId}")
    @ApiOperation(value = "专业详情" , notes = "专业详情")
    public Response selectMajorById(@PathVariable String majorId){
        return Response.success();
    }

    @DeleteMapping("/{majorId}")
    @ApiOperation(value = "删除专业" , notes = "删除专业")
    public Response deleteMajorById(@PathVariable String majorId){
        return Response.success();
    }

    @PostMapping("/add")
    @ApiOperation(value = "增加专业",notes = "增加专业")
    public Response addMajor(@RequestBody InsertMajorVo insertMajorVo){
        return Response.success();
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改专业",notes = "修改专业")
    public Response updateMajor(@RequestBody UpdateMajorVo updateMajorVo){
        return Response.success();
    }
}
