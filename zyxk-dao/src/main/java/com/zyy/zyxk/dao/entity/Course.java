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
 * @date 1/25/22 3:41 PM
 */
@TableName("course")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Course implements Serializable{
    private static final long serialVersionUID = 1L;

    @TableId(value = "course_id", type = IdType.ASSIGN_UUID)
    @DiyId(TableEnum.COURSE)
    @ApiModelProperty(value = "课程id")
    private String courseId;
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "授课地址")
    private String courseAddress;
    @ApiModelProperty(value = "授课老师")
    private String teacherId;
    @ApiModelProperty(value = "专业Id")
    private String majorId;
    @ApiModelProperty(value = "审核状态")
    private Integer auditStatus;
    @ApiModelProperty(value = "审核人")
    private String checkUser;
    @ApiModelProperty(value = "选择时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime choiceTime;
    @ApiModelProperty(value = "可选择人数")
    private Integer choiceAmount;
    @ApiModelProperty(value = "课程类型")
    private Integer courseType;
    @ApiModelProperty(value = "教学类型")
    private Integer teacherType;
    @ApiModelProperty(value = "创建者")
    private String creator;
    @ApiModelProperty(value = "开始抢课时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime starterTime;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "禁用状态")
    private Boolean isDel;


}
