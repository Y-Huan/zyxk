package com.zyy.zyxk.api.vo.Tutor;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Yang.H
 * @version 1.0
 * @date 4/5/22 9:36 PM
 */
@Data
public class CheckAuditTutorVo {
    @ApiModelProperty("审核单ID")
    private String auditTutorId;
    @ApiModelProperty("审核状态：0待审核，1已审核，2审核未通过")
    private Integer auditStatus;
}
