package com.zyy.zyxk.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyy.zyxk.api.vo.AuthorityVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import com.zyy.zyxk.common.annotation.FreeAuthentication;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.AuthorityService;
import com.zyy.zyxk.service.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/14/22 8:57 AM
 */
@Slf4j
@Api(tags = "权限模块")
@RestController
@RequestMapping("authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @PostMapping("addAuthority")
    @FreeAuthentication
    @ApiOperation("新增权限")
    public Response addAuthority(@RequestBody AuthorityVo authorityVo){
//        String token = request.getHeader("token");, HttpServletRequest request
//        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);

        UserJwtVo currentUser = new UserJwtVo();
        currentUser.setId("0000000000000001");
        if(authorityVo.getAuthorityType() == null){
            return Response.fail(ErrorCode.AUTHORITY_TYPE_NULL);
        }
        try{
            authorityService.addAuthority(authorityVo,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }

    @PostMapping("delAuthority")
    @ApiOperation("删除权限")
    public Response delAuthority(@RequestBody String authorityId){
        if(StringUtils.isEmpty(authorityId)){
            return Response.fail(ErrorCode.BIND_ERROR);
        }
        try{
            authorityService.delAuthority(authorityId);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }

    @PostMapping("updateAuthority")
    @ApiOperation("修改权限")
    public Response updateAuthority(@RequestBody AuthorityVo authorityVo){
        if(authorityVo.getAuthorityType() == null){
            return Response.fail(ErrorCode.AUTHORITY_TYPE_NULL);
        }
        if(StringUtils.isEmpty(authorityVo.getAuthorityId())){
            return Response.fail(ErrorCode.AUTHORITY_TYPE_NULL);
        }
        try{
            authorityService.updateAuthority(authorityVo);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }

    @GetMapping("list")
    @ApiOperation("权限列表")
    public Response list(BaseSelectVo baseSelectVo){
        List<AuthorityVo> authorityVos = new ArrayList<>();
        try{
            IPage page = new Page<>();
            PageUtil.setPage(baseSelectVo.getPageNo(), baseSelectVo.getPageSize(), page);
            authorityVos  = authorityService.getList(page,baseSelectVo);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("获取成功",authorityVos);
    }
}
