package com.zyy.zyxk.api.vo.college;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author fl
 * @date 2022-02-28
 **/
@Data
@ApiModel(value = "增加分院")
public class InsertCollegeVo {
    @ApiModelProperty(value = "分院Id")
    private String collegeId;
    @ApiModelProperty(value = "分院名称")
    private String collegeName;
    @ApiModelProperty(value = "学校ID")
    private String schoolId;
    @ApiModelProperty(value = "院长")
    private String dean;
    @ApiModelProperty(value = "创建者")
    private String creator;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "是否启用")
    private Boolean isDel;

}
