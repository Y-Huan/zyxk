package com.zyy.zyxk.api.vo.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * </p>
 *
 * @author Yang.H
 * @since 2021-06-17
 */
@Data
public class RoleAuthorityVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色权限id")
    private String roleAuthorityId;

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("权限id")
    private String authorityId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色说明")
    private String roleComment;

    @ApiModelProperty("父权限id")
    private String parentAuthorityId;

    @ApiModelProperty("权限名称")
    private String authorityName;

    @ApiModelProperty("权限类别：1、api；2、菜单；3、按钮")
    private Integer authorityType;

    @ApiModelProperty("启用标识：true、启用；false、禁用（默认）")
    private Boolean isEnable;

}
