package com.zyy.zyxk.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyy.zyxk.api.vo.AuthorityVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.dao.entity.SysAuthority;
import com.zyy.zyxk.service.AuthorityService;
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
    @ApiOperation(value = "新增权限",notes = "新增权限")
    public Response addAuthority(@RequestBody AuthorityVo authorityVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(authorityVo.getAuthorityType() == null){
            return Response.fail(ErrorCode.AUTHORITY_TYPE_NULL);
        }
        authorityService.addAuthority(authorityVo,currentUser);
        return Response.success("更新成功");
    }

    @PostMapping("delAuthority")
    @ApiOperation(value = "删除权限",notes ="删除权限" )
    public Response delAuthority(@RequestBody  AuthorityVo authorityVo){
        if(StringUtils.isEmpty(authorityVo.getAuthorityId())){
            return Response.fail(ErrorCode.BIND_ERROR);
        }
        authorityService.delAuthority(authorityVo.getAuthorityId());
        return Response.success("更新成功");
    }

    @PostMapping("updateAuthority")
    @ApiOperation(value = "修改权限",notes ="修改权限" )
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
    @ApiOperation(value = "权限列表",notes ="权限列表" )
    public Response<IPage<AuthorityVo>> list(BaseSelectVo baseSelectVo){

        IPage<AuthorityVo> page = new Page<>();
        PageUtil.setPage(baseSelectVo.getPageNo(), baseSelectVo.getPageSize(), page);
        IPage<AuthorityVo> authorityVos  = authorityService.getList(page,baseSelectVo);

        return Response.success("获取成功",authorityVos);
    }

    @GetMapping("detail")
    @ApiOperation("权限详情")
    public Response detail(String authorityId){
        if(StringUtils.isEmpty(authorityId)){
            return Response.fail(ErrorCode.AUTHORITY_NULL);
        }
        SysAuthority authorityVo = authorityService.deatil(authorityId);
        return Response.success("获取成功",authorityVo);
    }
}
