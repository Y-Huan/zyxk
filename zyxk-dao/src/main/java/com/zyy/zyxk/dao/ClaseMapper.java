package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyy.zyxk.api.vo.clase.ClaseListVo;
import com.zyy.zyxk.api.vo.clase.SelectClaseVo;
import com.zyy.zyxk.api.vo.clase.UpdateClaseVo;
import com.zyy.zyxk.dao.entity.Clase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fl
 * @version 1.0
 * @date 2022-02-21
 * 班级
 */
public interface ClaseMapper extends BaseMapper<Clase> {

    /**
     * 班级列表
     * @param selectClaseVo
     * @return
     */
    List<ClaseListVo> selectClaseList(SelectClaseVo selectClaseVo);

    /**
     * 班级详情
     * @param claseId
     * @return
     */
    Clase selectClaseById(@Param(value = "claseId") String claseId);

    /**
     * 修改班级
     * @param updateClaseVo
     * @return
     */
    int updateClase(UpdateClaseVo updateClaseVo);
}
