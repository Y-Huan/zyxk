package com.zyy.zyxk.api.vo.college;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author fl
 * @date 2022-02-28
 * 专业
 **/
@Data
@ApiModel(value = "分院列表")
public class CollegeListVo {
    @ApiModelProperty(value = "分院Id")
    private String collegeId;
    @ApiModelProperty(value = "分院名称")
    private String collegeName;
    @ApiModelProperty(value = "学校ID")
    private String schoolId;
    @ApiModelProperty(value = "院长")
    private String dean;
    @ApiModelProperty(value = "学校名称")
    private String schoolName;
    @ApiModelProperty(value = "学校地址")
    private String schoolAddress;
    @ApiModelProperty(value = "院长手机号")
    private String deanPhone;

    private LocalDateTime createTime;
}
