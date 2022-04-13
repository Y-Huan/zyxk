package com.zyy.zyxk.api.vo.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author fl
 * @date 2022-02-28
 **/
@Data
@ApiModel(value = "课程")
public class CourseVo {
    @ApiModelProperty(value = "课程id")
    private String courseId;
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "专业Id")
    private String majorId;
    @ApiModelProperty(value = "专业名称")
    private String majorName;
    @ApiModelProperty(value = "课程类型")
    private Integer courseType;
    @ApiModelProperty(value = "教学类型")
    private Integer teacherType;
    @ApiModelProperty(value = "授课地址")
    private String courseAddress;
    @ApiModelProperty(value = "授课老师")
    private String teacherId;
    @ApiModelProperty(value = "授课老师名称")
    private String teacherName;
    @ApiModelProperty(value = "审核状态")
    private Integer auditStatus;
    @ApiModelProperty(value = "审核人")
    private String checkUser;
    @ApiModelProperty(value = "选择时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime choiceTime;
    @ApiModelProperty(value = "可选择人数")
    private Integer choiceAmount;
    @ApiModelProperty(value = "创建者")
    private String creator;
}
