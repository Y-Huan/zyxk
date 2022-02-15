package com.zyy.zyxk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.api.vo.RoleAuthorityVo;
import com.zyy.zyxk.api.vo.RoleVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.dao.entity.Role;

import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/14/22 2:46 PM
 */

public interface RoleService extends IService<Role> {
    //新增角色
    void addRole(RoleAuthorityVo roleAuthorityVo, UserJwtVo currentUser);
    //删除角色
    void delRole(String roleId);
    //编辑角色
    void updateRole(RoleAuthorityVo roleAuthorityVo,UserJwtVo currentUser);
    //角色列表
    List<RoleVo> getList();
}
