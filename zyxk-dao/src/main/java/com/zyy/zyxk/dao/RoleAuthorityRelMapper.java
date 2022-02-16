package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyy.zyxk.api.view.SysRoleAuthorityView;
import com.zyy.zyxk.dao.entity.RoleAuthorityRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 4:46 PM
 */
public interface RoleAuthorityRelMapper extends BaseMapper<RoleAuthorityRel> {
    void delAllRoleAuthority(@Param("roleId") String roleId);

    List<SysRoleAuthorityView> getList(@Param("roleId") String roleId);
}
