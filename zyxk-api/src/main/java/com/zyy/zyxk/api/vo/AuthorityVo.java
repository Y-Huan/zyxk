package com.zyy.zyxk.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/14/22 9:04 AM
 */
@Data
public class AuthorityVo {

    @ApiModelProperty("权限ID")
    private String authorityId;

    @ApiModelProperty("父级权限ID")
    private String parentAuthorityId;

    @ApiModelProperty("权限名称")
    private String authorityName;

    @ApiModelProperty("权限类型 1/api、2/菜单、3/按钮")
    private Integer authorityType;

    @ApiModelProperty("权限地址")
    private String authorityUrl;

    @ApiModelProperty("权限编码")
    private String authorityPermission;

    private LocalDateTime createTime;

}
