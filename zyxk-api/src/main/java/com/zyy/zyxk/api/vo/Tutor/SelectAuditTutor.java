package com.zyy.zyxk.api.vo.Tutor;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Yang.H
 * @version 1.0
 * @date 4/5/22 8:58 PM
 */
@Data
public class SelectAuditTutor {

    @ApiModelProperty("专业ID")
    private String majorId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;
}
