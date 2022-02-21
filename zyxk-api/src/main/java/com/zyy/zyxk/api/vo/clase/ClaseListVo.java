package com.zyy.zyxk.api.vo.clase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fl
 * @date 2022-02-21
 **/
@Data
@ApiModel(value = "班级列表")
public class ClaseListVo {
    @ApiModelProperty(value = "班级ID")
    private String claseId;
    @ApiModelProperty(value = "班级名称")
    private String claseName;
    @ApiModelProperty(value = "专业ID")
    private String majorId;
    @ApiModelProperty(value = "辅导员")
    private String instructor;
    @ApiModelProperty(value = "班主任ID")
    private String teacherId;
    @ApiModelProperty(value = "班主任名称")
    private String headmasterName;
    @ApiModelProperty(value = "班主任电话")
    private String headmasterPhone;
    @ApiModelProperty(value = "专业名称")
    private String majorName;

}