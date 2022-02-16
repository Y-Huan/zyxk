package com.zyy.zyxk.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyy.zyxk.dao.util.DiyId;
import com.zyy.zyxk.dao.util.TableEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 3:41 PM
 */
@TableName("course_techer_rel")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseTeacherRel implements Serializable{
    private static final long serialVersionUID = 1L;

    @TableId(value = "course_teacher_rel_id", type = IdType.ASSIGN_UUID)
    @DiyId(TableEnum.COURSE_TECHER_REL)
    private String courseTeacherRelId;

    private String courseId;

    private String teacherId;

    private String creator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private boolean isDel;


}
