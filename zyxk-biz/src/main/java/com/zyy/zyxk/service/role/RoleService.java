package com.zyy.zyxk.service.role;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.role.RoleAuthorityListVo;
import com.zyy.zyxk.api.vo.role.RoleVo;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.dao.entity.Role;

import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/14/22 2:46 PM
 */

public interface RoleService extends IService<Role> {
    //新增角色
    void addRole(RoleVo roleVo, UserJwtVo currentUser);
    //删除角色
    void delRole(String roleId);
    //编辑角色
    void updateRole(RoleAuthorityListVo roleAuthorityVo, UserJwtVo currentUser);
    //角色列表
    List<RoleVo> getList(IPage page,String selectStringKey);
    //获取角色权限列表
    Response getAuthorityList(String roleId);
}
