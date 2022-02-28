package com.zyy.zyxk.api.vo.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    @ApiModelProperty(value = "课程类型")
    private Integer type;
    @ApiModelProperty(value = "教学类型")
    private Integer teachType;
}
