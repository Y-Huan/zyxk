package com.zyy.zyxk.web.controller;

import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.college.CollegeListVo;
import com.zyy.zyxk.api.vo.college.InsertCollegeVo;
import com.zyy.zyxk.api.vo.college.SelectCollegeVo;
import com.zyy.zyxk.api.vo.college.UpdateCollegeVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.college.CollegeService;
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
 * @date 2022-02-28
 **/
@Slf4j
@RequestMapping("/college")
@RestController
@Api(tags = "分院模块")
public class CollegeController {

    @Resource
    private CollegeService collegeService;

    @PostMapping("/list")
    @ApiOperation(value = "分院列表", notes = "分院列表")
    public Response selectCollegeList(@RequestBody SelectCollegeVo selectCollegeVo){
        List<CollegeListVo> collegeListVoList = new ArrayList<>();
        try {
            collegeListVoList = collegeService.selectCollegeList(selectCollegeVo);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("获取成功",collegeListVoList);
    }

    @GetMapping("/{collegeId}")
    @ApiOperation(value = "分院详情",notes = "分院详情")
    public Response selectCollegeById(@PathVariable String collegeId){
        CollegeListVo collegeListVo = collegeService.selectCollegeById(collegeId);
        return Response.success("获取成功",collegeListVo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "增加分院" , notes = "增加分院")
    public Response insertCollege(@RequestBody InsertCollegeVo insertCollegeVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(insertCollegeVo.getCollegeName())){
            return Response.fail(ErrorCode.No_College_Name);
        }

        try {
            collegeService.insertCollege(insertCollegeVo,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改分院" , notes = "修改分院")
    public Response updateCollege(@RequestBody UpdateCollegeVo updateCollegeVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        try {
            collegeService.updateCollege(updateCollegeVo,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }

    @DeleteMapping("/{collegeId}")
    @ApiOperation(value = "删除分院" , notes = "删除分院")
    public Response deleteCollege(@PathVariable String collegeId){
        if(StringUtils.isEmpty(collegeId)){
            return Response.fail(ErrorCode.College_Id_Invalid);
        }
        try {
            collegeService.deleteCollege(collegeId);
        }catch (Exception e){
            log.info(e.getMessage());
        }

        return Response.success("更新成功");
    }
}
