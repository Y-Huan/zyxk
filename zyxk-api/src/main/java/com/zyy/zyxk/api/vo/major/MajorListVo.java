package com.zyy.zyxk.api.vo.major;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fl
 * @date 2022-02-15
 * 专业
 **/
@Data
@ApiModel(value = "专业")
public class MajorListVo {
    @ApiModelProperty(value = "专业id", name = "majorId", example = "1")
    private String majorId;

    @ApiModelProperty(value = "分院id", name = "collegeId", example = "1")
    private String collegeId;

    @ApiModelProperty(value = "分院名称", name = "collegeName", example = "1")
    private String collegeName;

    @ApiModelProperty(value = "专业名字", name = "majorName", example = "移动应用开发")
    private String majorName;

    @ApiModelProperty(value = "专业负责人id", name = "personInChargeId", example = "1")
    private String personInChargeId;

    @ApiModelProperty(value = "专业负责人名字", name = "personInChargeName", example = "1")
    private String personInChargeName;

    @ApiModelProperty(value = "专业负责人联系电话", name = "phone", example = "15999999999")
    private String phone;
}
