package com.zyy.zyxk.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyy.zyxk.dao.util.DiyId;
import com.zyy.zyxk.dao.util.TableEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Yang.H
 * @version 1.0
 * @date 4/5/22 11:13 PM
 */
@TableName("student_tutor_rel")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StudentTutorRel {

    @TableId(value = "student_tutor_rel_id",type = IdType.ASSIGN_UUID)
    @DiyId(TableEnum.STUDENT_TUTOR_REL)
    private String studentTutorId;

    private String studentId;

    private String tutorId;

    private String creator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDel;
}
