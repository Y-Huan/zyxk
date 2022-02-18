package com.zyy.zyxk.web.controller;

import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.major.InsertMajorVo;
import com.zyy.zyxk.api.vo.major.MajorListVo;
import com.zyy.zyxk.api.vo.major.SelectMajorVo;
import com.zyy.zyxk.api.vo.major.UpdateMajorVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.major.MajorService;
import com.zyy.zyxk.service.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        MajorListVo major = majorService.selectedMajorById(majorId);
        return Response.success("查询成功",major);
    }

    @DeleteMapping("/{majorId}")
    @ApiOperation(value = "删除专业" , notes = "删除专业")
    public Response deleteMajorById(@PathVariable String majorId){
        if(StringUtils.isEmpty(majorId)){
            return Response.fail(ErrorCode.Major_Id_Invalid);
        }
        try {
            majorService.deleteMajor(majorId);
        }catch (Exception e){
            log.info(e.getMessage());
        }

        return Response.success("更新成功");

    }

    @PostMapping("/add")
    @ApiOperation(value = "增加专业",notes = "增加专业")
    public Response addMajor(@RequestBody InsertMajorVo insertMajorVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(insertMajorVo.getMajorName())){
            return Response.fail(ErrorCode.No_Major_Name);
        }

        try {
            majorService.addMajor(insertMajorVo,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改专业",notes = "修改专业")
    public Response updateMajor(@RequestBody UpdateMajorVo updateMajorVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        try {
            majorService.updateMajor(updateMajorVo,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }
}
