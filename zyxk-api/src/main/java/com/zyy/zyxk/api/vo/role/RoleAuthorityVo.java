package com.zyy.zyxk.api.vo.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/14/22 2:34 PM
 */
@Data
public class RoleAuthorityVo {

    @ApiModelProperty("角色Id")
    private String roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色所有权限ID")
    private List<String> authoritys;
}
