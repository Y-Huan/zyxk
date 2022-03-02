package com.zyy.zyxk.api.vo.role;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/15/22 9:43 AM
 */
@Data
public class RoleVo {

    private String roleId;

    private String roleName;

    private String roleComment;

    private String creator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
