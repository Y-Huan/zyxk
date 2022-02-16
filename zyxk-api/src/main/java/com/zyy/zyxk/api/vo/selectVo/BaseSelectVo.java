package com.zyy.zyxk.api.vo.selectVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseSelectVo implements Serializable {

    @ApiModelProperty("查询页")
    Long pageNo;

    @ApiModelProperty("查询数量")
    Long pageSize;

    @ApiModelProperty("模糊搜索/字符串")
    String selectStringKey;

    @ApiModelProperty("数值查询")
    Long selectLongKey;

}
