package com.zyy.zyxk.api.vo.clase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fl
 * @date 2022-02-21
 **/
@Data
@ApiModel(value = "查询班级")
public class SelectClaseVo {
    @ApiModelProperty(value = "班级名称")
    private String claseName;
    @ApiModelProperty(value = "老师(辅导员)名称")
    private String teacherName;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "专业名称")
    private String majorName;
}
