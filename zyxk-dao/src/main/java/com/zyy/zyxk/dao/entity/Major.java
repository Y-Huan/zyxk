package com.zyy.zyxk.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 3:41 PM
 */
@TableName("major")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Major implements Serializable{
    private static final long serialVersionUID = 1L;

    @TableId(value = "major_id", type = IdType.ASSIGN_UUID)
    private String majorId;

    private String majorName;

    private String collegeId;

    private String personInCharge;

    private String creator;

    private Data createTime;

    private Data updateTime;

    private boolean isDel;


}
