package com.zyy.zyxk.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.role.RoleAuthorityListVo;
import com.zyy.zyxk.api.vo.role.RoleVo;
import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.role.RoleService;
import com.zyy.zyxk.service.util.JwtUtil;
import com.zyy.zyxk.service.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/14/22 8:52 AM
 */

@Slf4j
@Api(tags = "角色模块")
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("addRple")
    @ApiOperation(value = "新增角色" ,notes = "新增角色")
    public Response addRole(@RequestBody RoleVo roleVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(roleVo.getRoleName())){
            return Response.fail(ErrorCode.No_Role_Name);
        }

        try {
            roleService.addRole(roleVo,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }

    @PostMapping("delRole")
    @ApiOperation(value = "删除角色",notes = "删除角色")
    public Response delRole(@RequestBody String roleId){
        if(StringUtils.isEmpty(roleId)){
            return Response.fail(ErrorCode.Role_Id_Invalid);
        }
        try {
            roleService.delRole(roleId);
        }catch (Exception e){
            log.info(e.getMessage());
        }

        return Response.success("更新成功");
    }

    @PostMapping("updateRole")
    @ApiOperation(value = "编辑角色" ,notes = "编辑角色")
    public Response upodateRole(@RequestBody RoleAuthorityListVo roleAuthorityVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if (roleAuthorityVo.getAuthoritys().size() <= 0 ){
            return Response.fail(ErrorCode.No_Authorities);
        }
        if(StringUtils.isEmpty(roleAuthorityVo.getRoleName())){
            return Response.fail(ErrorCode.No_Role_Name);
        }
        try {
            roleService.updateRole(roleAuthorityVo,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }

    @GetMapping("list")
    @ApiOperation(value = "角色列表",notes = "角色列表")
    public Response list(BaseSelectVo baseSelectVo){
        List<RoleVo> roleVos = new ArrayList<>();
        try {
            IPage page = new Page<>();
            PageUtil.setPage(baseSelectVo.getPageNo(), baseSelectVo.getPageSize(), page);
            roleVos = roleService.getList(page,baseSelectVo.getSelectStringKey());
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("获取成功",roleVos);
    }

    @GetMapping("roleAuthorityList")
    @ApiOperation(value = "获取角色权限列表",notes = "获取角色权限列表")
    public  Response roleAuthorityList(String roleId){
        if(roleId ==null){
            return Response.fail(ErrorCode.No_Role_Id);
        }
        return roleService.getAuthorityList(roleId);
    }
}
