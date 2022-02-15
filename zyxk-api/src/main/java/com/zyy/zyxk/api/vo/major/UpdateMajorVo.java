package com.zyy.zyxk.api.vo.major;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author fl
 * @date 2022-02-15
 **/
@Data
public class UpdateMajorVo {
    private String majorId;

    private String majorName;

    private String collegeId;

    private String personInChargeId;

    private String creator;

    private LocalDateTime updateTime;

    private boolean isDel;
}
