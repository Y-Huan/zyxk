package com.zyy.zyxk.api.vo.Tutor;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Yang.H
 * @version 1.0
 * @date 4/3/22 9:17 PM
 */
@Data
public class TutorVo {
    @ApiModelProperty(value = "导师id")
    private String tutorId;
    @ApiModelProperty("审核单ID")
    private String auditTutorId;
    @ApiModelProperty("教师Id")
    private String teacherId;
    @ApiModelProperty("教师Name")
    private String teacherName;
    @ApiModelProperty("专业ID")
    private String majorId;
    @ApiModelProperty("专业名称")
    private String majorName;
    @ApiModelProperty("可选时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime choiceTime;
    @ApiModelProperty("可选人数")
    private Integer choiceAmount;
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
