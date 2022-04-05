package com.zyy.zyxk.api.vo.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Yang.H
 * @version 1.0
 * @date 3/30/22 10:22 PM
 */
@Data
public class ChekcCourseVo {
    private String courseId;

    private Integer auditStatus;

    @ApiModelProperty(value = "开始抢课时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime choiceTime;

}
