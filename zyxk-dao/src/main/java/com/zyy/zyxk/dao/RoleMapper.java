package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyy.zyxk.api.vo.role.RoleVo;
import com.zyy.zyxk.dao.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 4:46 PM
 */
public interface RoleMapper extends BaseMapper<Role> {
    //角色列表
    List<RoleVo> getList(IPage page, @Param("selectStringKey") String selectStringKey);
}
