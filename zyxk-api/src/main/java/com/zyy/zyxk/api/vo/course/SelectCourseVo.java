package com.zyy.zyxk.api.vo.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fl
 * @date 2022-02-28
 **/
@Data
@ApiModel(value = "课程查询")
public class SelectCourseVo {
    @ApiModelProperty("查询页")
    Long pageNo;

    @ApiModelProperty("查询数量")
    Long pageSize;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "课程类型")
    private Integer type;

    @ApiModelProperty(value = "教学类型")
    private Integer teacherType;
}
