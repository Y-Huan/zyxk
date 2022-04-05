package com.zyy.zyxk.api.vo.Tutor;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 4/3/22 9:46 PM
 */
@Data
public class AuditTutorVo {

    @ApiModelProperty("审核单ID")
    private String auditTutorId;
    @ApiModelProperty("审核状态：0待审核，1已审核，2审核未通过")
    private Integer auditStatus;
    @ApiModelProperty("审核人")
    private String checkUser;
    @ApiModelProperty("专业ID")
    private String majorId;
    @ApiModelProperty("专业名称")
    private String majorName;
    @ApiModelProperty("可选时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime choiceTime;
    @ApiModelProperty("导师")
    private List<TutorVo> tutorVos;
    @ApiModelProperty("创建人")
    private String creator;
    @ApiModelProperty("创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime createTime;
    @ApiModelProperty("更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime updateTime;
    @ApiModelProperty("启用标识")
    private Boolean isDel;

}
