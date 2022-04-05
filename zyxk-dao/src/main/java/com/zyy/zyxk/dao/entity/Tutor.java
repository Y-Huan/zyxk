package com.zyy.zyxk.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zyy.zyxk.dao.util.DiyId;
import com.zyy.zyxk.dao.util.TableEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Yang.H
 * @version 1.0
 * @date 4/3/22 9:07 PM
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tutor")
public class Tutor implements Serializable {
    @ApiModelProperty("导师ID")
    @TableId(value = "tutor_id", type = IdType.ASSIGN_UUID)
    @DiyId(TableEnum.TUTOR)
    private String tutorId;
    @ApiModelProperty("审核单ID")
    private String auditTutorId;
    @ApiModelProperty("教师Id")
    private String teacherId;
    @ApiModelProperty("专业ID")
    private String majorId;
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
