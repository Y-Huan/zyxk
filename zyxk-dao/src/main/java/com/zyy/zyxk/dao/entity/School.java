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
@TableName("school")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class School implements Serializable{
    private static final long serialVersionUID = 1L;

    @TableId(value = "school_id", type = IdType.ASSIGN_UUID)
    @DiyId(TableEnum.SCHOOL)
    private String schoolId;

    private String schoolName;

    private String schoolAddress;

    private String headmaster;

    private String schoolSide;

    private String creator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private boolean isDel;


}
