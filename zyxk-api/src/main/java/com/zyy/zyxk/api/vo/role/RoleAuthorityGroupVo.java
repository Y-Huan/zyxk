package com.zyy.zyxk.api.vo.role;

import com.zyy.zyxk.api.vo.BaseVo;
import lombok.Data;

import java.util.List;

/**
 *
 * @Description 角色权限关系分组vo
 * @Author Yang.H
 * @Date 2021/8/31
 *
 **/
@Data
public class RoleAuthorityGroupVo extends BaseVo {
    private RoleAuthorityVo menu;

    private List<RoleAuthorityVo> authorities;

}
