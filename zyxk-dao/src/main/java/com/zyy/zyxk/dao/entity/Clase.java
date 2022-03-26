package com.zyy.zyxk.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @date 1/25/22 3:41 PM
 */
@TableName("clase")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Clase implements Serializable{
    private static final long serialVersionUID = 1L;

    @TableId(value = "clase_id", type = IdType.ASSIGN_UUID)
    @DiyId(TableEnum.CLASE)
    @ApiModelProperty(value = "班级ID")
    private String claseId;
    @ApiModelProperty(value = "班级名称")
    private String claseName;
    @ApiModelProperty(value = "专业ID")
    private String majorId;
    @ApiModelProperty(value = "学校ID")
    private String schoolId;
    @ApiModelProperty(value = "教师ID")
    private String teacherId;
    @ApiModelProperty(value = "创建者")
    private String creator;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "删除标志")
    private Boolean isDel;


}
