package com.zyy.zyxk.dao.entity;

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
@TableName("sequence")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Sequence implements Serializable{

    private String tableName;

    private String sequence;



}
