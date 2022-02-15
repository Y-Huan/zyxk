package com.zyy.zyxk.api.vo.major;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author fl
 * @date 2022-02-15
 * 专业
 **/
@Data
@ApiModel(value = "增加专业")
public class InsertMajorVo {
    private String majorId;

    private String majorName;

    private String collegeId;

    private String personInChargeId;

    private String creator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private boolean isDel;
}
