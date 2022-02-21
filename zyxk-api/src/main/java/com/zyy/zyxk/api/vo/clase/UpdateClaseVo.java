package com.zyy.zyxk.api.vo.clase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author fl
 * @date 2022-02-21
 **/
@ApiModel(value = "修改班级")
@Data
public class UpdateClaseVo {
    @ApiModelProperty(value = "班级ID")
    private String claseId;
    @ApiModelProperty(value = "班级名称")
    private String claseName;
    @ApiModelProperty(value = "专业ID")
    private String majorId;
    @ApiModelProperty(value = "辅导员")
    private String instructor;
    @ApiModelProperty(value = "教师ID")
    private String teacherId;
    @ApiModelProperty(value = "创建者")
    private String creator;
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "删除标志")
    private Boolean isDel;
}
