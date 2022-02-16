package com.zyy.zyxk.api.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wang gang
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleAuthorityView implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roleAuthorityId;

    private String roleId;

    private String authorityId;

    private String roleName;

    private String roleComment;

    private String parentAuthorityId;

    private String authorityName;

    private Integer authorityType;

    private Boolean isEnable;

}
