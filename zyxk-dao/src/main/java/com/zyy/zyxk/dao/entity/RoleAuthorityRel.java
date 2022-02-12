package com.zyy.zyxk.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 3:41 PM
 */
@TableName("role_authority_rel")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleAuthorityRel implements Serializable{
    private static final long serialVersionUID = 1L;

    @TableId(value = "role_authority_rel_id", type = IdType.ASSIGN_UUID)
    private String roleAuthorityRelId;

    private String roleId;

    private String authorityId;

    private String creator;

    private Data createTime;

    private Data updateTime;

    private boolean isDel;


}
