package com.zyy.zyxk.api.vo.school;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/19/22 3:51 PM
 */
@Data
public class SchoolVo {

    @ApiModelProperty("学校id")
    private String schoolId;

    @ApiModelProperty("学校名称")
    private String schoolName;

    @ApiModelProperty("学校详细地址")
    private String schoolAddress;

    @ApiModelProperty("校长")
    private String headmaster;

    @ApiModelProperty("学校简介")
    private String schoolSide;

    private String creator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDel;
}
