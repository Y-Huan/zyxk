package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyy.zyxk.api.vo.school.SchoolVo;
import com.zyy.zyxk.dao.entity.School;
import org.apache.ibatis.annotations.Param;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 4:46 PM
 */
public interface SchoolMapper extends BaseMapper<School> {
    IPage<SchoolVo> getList(IPage<SchoolVo> page,
                            @Param("selectStringKey") String selectStringKey);

    SchoolVo getDetail(@Param("schoolId") String schoolId);
}
