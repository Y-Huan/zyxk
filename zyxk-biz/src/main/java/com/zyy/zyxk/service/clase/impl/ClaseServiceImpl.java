package com.zyy.zyxk.service.clase.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.clase.ClaseListVo;
import com.zyy.zyxk.api.vo.clase.InsertClaseVo;
import com.zyy.zyxk.api.vo.clase.SelectClaseVo;
import com.zyy.zyxk.api.vo.clase.UpdateClaseVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.dao.ClaseMapper;
import com.zyy.zyxk.dao.entity.Clase;
import com.zyy.zyxk.service.clase.ClaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fl
 * @date 2022-02-21
 * 班级
 **/
@Service
public class ClaseServiceImpl extends ServiceImpl<ClaseMapper, Clase> implements ClaseService {

    @Resource
    private ClaseMapper claseMapper;
    /**
     * 班级列表
     *
     * @param selectClaseVo
     * @return
     */
    @Override
    public List<ClaseListVo> selectClaseList(SelectClaseVo selectClaseVo) {
        return claseMapper.selectClaseList(selectClaseVo);
    }

    /**
     * 班级详情
     *
     * @param claseId
     * @return
     */
    @Override
    public Clase selectClaseById(String claseId) {
        return claseMapper.selectClaseById(claseId);
    }

    /**
     * 修改班级
     *
     * @param updateClaseVo
     * @return
     */
    @Override
    public int updateClase(UpdateClaseVo updateClaseVo, UserJwtVo currentUser) {
        updateClaseVo.setUpdateTime(LocalDateTime.now());
        updateClaseVo.setCreator(currentUser.getUserName());
        return claseMapper.updateClase(updateClaseVo);
    }

    /**
     * 增加班级
     *
     * @param insertClaseVo
     */
    @Override
    public void addClase(InsertClaseVo insertClaseVo, UserJwtVo currentUser) {
        Clase clase = new Clase();
        clase.setClaseName(insertClaseVo.getClaseName());
        clase.setInstructor(insertClaseVo.getInstructor());
        clase.setIsDel(true);
        clase.setMajorId(insertClaseVo.getMajorId());
        clase.setTeacherId(insertClaseVo.getTeacherId());
        clase.setCreateTime(LocalDateTime.now());
        clase.setCreator(currentUser.getUserName());
        claseMapper.insert(clase);
    }

    /**
     * 删除班级
     *
     * @param claseId
     */
    @Override
    public void deleteClase(String claseId) {
        QueryWrapper<Clase> majorQueryWrapper = new QueryWrapper<>();
        majorQueryWrapper.eq("clase_id",claseId);
        majorQueryWrapper.eq("is_del",true);
        Clase clase= claseMapper.selectOne(majorQueryWrapper);
        if(clase == null){
            throw new BizException(ErrorCode.Clase_Id_Invalid);
        }
        clase.setIsDel(false);
        clase.setUpdateTime(LocalDateTime.now());
        claseMapper.updateById(clase);
    }
}
