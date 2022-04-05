package com.zyy.zyxk.api.vo.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author fl
 * @date 2022-02-28
 **/
@Data
@ApiModel(value = "修改课程")
public class UpdateCourseVo {
    @ApiModelProperty(value = "课程id")
    private String courseId;
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    @ApiModelProperty(value = "专业Id")
    private String majorId;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "授课地址")
    private String courseAddress;
    @ApiModelProperty(value = "课程类型")
    private Integer courseType;
    @ApiModelProperty(value = "教学类型")
    private Integer teachType;
    @ApiModelProperty(value = "创建者")
    private String creator;
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "禁用状态")
    private Boolean isDel;
}
