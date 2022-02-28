package com.zyy.zyxk.api.vo.college;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fl
 * @date 2022-02-28
 **/
@Data
@ApiModel(value = "分院查询")
public class SelectCollegeVo {
    @ApiModelProperty("查询页")
    Long pageNo;

    @ApiModelProperty("查询数量")
    Long pageSize;

    @ApiModelProperty(value = "分院名称")
    private String collegeName;
    @ApiModelProperty(value = "院长")
    private String dean;
}
