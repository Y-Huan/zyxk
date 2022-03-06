package com.zyy.zyxk.api.vo.major;

import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fl
 * @date 2022-02-15
 * 查询专业
 **/
@Data
@ApiModel(value = "查询专业")
public class SelectMajorVo extends BaseSelectVo {

    @ApiModelProperty(value = "专业名字", name = "majorName", example = "移动应用开发")
    private String majorName;

    @ApiModelProperty(value = "专业负责人", name = "personInCharge", example = "测试")
    private String personInChargeName;

    @ApiModelProperty(value = "专业负责人联系电话", name = "phone", example = "15999999999")
    private String phone;
}
