package com.zyy.zyxk.api.vo.student;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Yang.H
 * @version 1.0
 * @date 4/5/22 10:13 PM
 */
@Data
public class StudentVo {
    private String studentId;

    private String studentNumber;

    private String schoolId;

    private String claseId;

    private String majorId;

    private String studentName;

    private String phone;

    private String password;

    private String salt;

    private Integer sex;

    private String creator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDel;
}
