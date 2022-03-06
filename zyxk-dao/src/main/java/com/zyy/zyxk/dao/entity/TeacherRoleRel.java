package com.zyy.zyxk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyy.zyxk.dao.util.DiyId;
import com.zyy.zyxk.dao.util.TableEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@TableName("teacher_role_rel")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TeacherRoleRel {
    @TableId("teacher_role_rel_id")
    @DiyId(TableEnum.TEACHER_ROLE_REL)
    private String teacherRoleRelId;

    private String roleId;

    private String teacherId;

    private String creator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDel;

}
