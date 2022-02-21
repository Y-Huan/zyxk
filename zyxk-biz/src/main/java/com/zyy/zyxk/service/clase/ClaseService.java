package com.zyy.zyxk.service.clase;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.clase.ClaseListVo;
import com.zyy.zyxk.api.vo.clase.InsertClaseVo;
import com.zyy.zyxk.api.vo.clase.SelectClaseVo;
import com.zyy.zyxk.api.vo.clase.UpdateClaseVo;
import com.zyy.zyxk.dao.entity.Clase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fl
 * @date 2022-02-21
 * 班级
 **/
public interface ClaseService extends IService<Clase> {
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
    int updateClase(UpdateClaseVo updateClaseVo, UserJwtVo currentUser);

    /**
     * 增加班级
     * @param insertClaseVo
     */
    void addClase(InsertClaseVo insertClaseVo, UserJwtVo currentUser);

    /**
     * 删除班级
     * @param claseId
     */
    void deleteClase(@Param(value = "claseId")String claseId);
}
