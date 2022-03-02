package com.zyy.zyxk.api.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeacherVo {
    private String teacherId;

    private String phone;

    private String teacherName;

    private String schoolId;

    private String schoolName;

    private String roleId;

    private String roleName;

    private String password;

    private String salt;

    private Integer sex;

    private String creator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDel;
}
