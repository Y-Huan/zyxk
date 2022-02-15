package com.zyy.zyxk.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 3:41 PM
 */
@TableName("sys_authority")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysAuthority implements Serializable{
    private static final long serialVersionUID = 1L;

    @TableId(value = "authority_id", type = IdType.ASSIGN_UUID)
    private String authorityId;

    private String parentAuthorityId;

    private String authorityName;

    private Integer authorityType;

    private String authorityUrl;

    private String authorityPermission;

    private String creator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private boolean isDel;


}
