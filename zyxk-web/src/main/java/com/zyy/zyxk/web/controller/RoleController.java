package com.zyy.zyxk.web.controller;

import com.zyy.zyxk.api.vo.RoleAuthorityVo;
import com.zyy.zyxk.api.vo.RoleVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.service.RoleService;
import com.zyy.zyxk.service.util.JwtUtil;
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

    @Autowired(required=false)
    private RoleService roleService;

    @PostMapping("addRple")
    @ApiOperation("新增角色")
    public Response addRole(@RequestBody RoleAuthorityVo roleAuthorityVo, HttpServletRequest request){
        String token = request.getHeader("token");
        UserJwtVo currentUser = JwtUtil.getCurrentUser(token);
        if(StringUtils.isEmpty(roleAuthorityVo.getRoleName())){
            return Response.fail(ErrorCode.No_Role_Name);
        }
        if (roleAuthorityVo.getAuthoritys().size() <= 0 ){
            return Response.fail(ErrorCode.No_Authorities);
        }
        try {
            roleService.addRole(roleAuthorityVo,currentUser);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("更新成功");
    }

    @PostMapping("delRole")
    @ApiOperation("删除角色")
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
    @ApiOperation("编辑角色")
    public Response upodateRole(@RequestBody RoleAuthorityVo roleAuthorityVo, HttpServletRequest request){
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
    @ApiOperation("角色列表")
    public Response list(){
        List<RoleVo> roleVos = new ArrayList<>();
        try {
            roleVos = roleService.getList();
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return Response.success("获取成功",roleVos);
    }

}
