package com.zyy.zyxk.api.vo.clase;

import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fl
 * @date 2022-02-21
 **/
@Data
@ApiModel(value = "查询班级")
public class SelectClaseVo extends BaseSelectVo {
    @ApiModelProperty(value = "班级名称")
    private String claseName;
    @ApiModelProperty(value = "专业名称")
    private String majorName;
}
