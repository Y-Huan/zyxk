package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyy.zyxk.dao.entity.Sequence;
import org.apache.ibatis.annotations.Param;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 4:46 PM
 */
public interface SequenceMapper extends BaseMapper<Sequence> {
    void updateSequence(@Param("tableName")String tableName);

    Integer getNewAddId(@Param("tableName") String tableName);
}
