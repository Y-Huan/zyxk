package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyy.zyxk.api.vo.clase.ClaseListVo;
import com.zyy.zyxk.api.vo.clase.UpdateClaseVo;
import com.zyy.zyxk.dao.entity.Clase;
import org.apache.ibatis.annotations.Param;

/**
 * @author fl
 * @version 1.0
 * @date 2022-02-21
 * 班级
 */
public interface ClaseMapper extends BaseMapper<Clase> {

    /**
     * 班级列表

     * @return
     */
    IPage<ClaseListVo> selectClaseList(IPage<ClaseListVo> page,
                                       @Param("claseName")String claseName,
                                       @Param("majorName")String majorName,
                                       @Param("schoolId")String schoolId);

    /**
     * 班级详情
     * @param claseId
     * @return
     */
    ClaseListVo selectClaseById(@Param("claseId") String claseId);

    /**
     * 修改班级
     * @param updateClaseVo
     * @return
     */
    int updateClase(UpdateClaseVo updateClaseVo);
}
