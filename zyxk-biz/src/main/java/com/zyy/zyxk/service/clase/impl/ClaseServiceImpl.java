package com.zyy.zyxk.service.clase.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.clase.ClaseListVo;
import com.zyy.zyxk.api.vo.clase.InsertClaseVo;
import com.zyy.zyxk.api.vo.clase.SelectClaseVo;
import com.zyy.zyxk.api.vo.clase.UpdateClaseVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.util.BeanUtil;
import com.zyy.zyxk.dao.ClaseMapper;
import com.zyy.zyxk.dao.StudentMapper;
import com.zyy.zyxk.dao.entity.Clase;
import com.zyy.zyxk.dao.entity.Student;
import com.zyy.zyxk.service.clase.ClaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fl
 * @date 2022-02-21
 * 班级
 **/
@Service
public class ClaseServiceImpl extends ServiceImpl<ClaseMapper, Clase> implements ClaseService {

    @Autowired
    private ClaseMapper claseMapper;


    @Autowired
    private StudentMapper studentMapper;
    /**
     * 班级列表
     *
     * @param selectClaseVo
     * @return
     */
    @Override
    public IPage<ClaseListVo> selectClaseList(IPage<ClaseListVo> page,SelectClaseVo selectClaseVo,UserJwtVo currentUser) {
        return claseMapper.selectClaseList(page,selectClaseVo.getClaseName(),selectClaseVo.getMajorName(),currentUser.getSchoolId());
    }

    /**
     * 班级详情
     *
     * @param claseId
     * @return
     */
    @Override
    public ClaseListVo selectClaseById(String claseId) {
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
        if(checkName(updateClaseVo.getClaseName(),updateClaseVo.getClaseId())){
            throw new BizException(ErrorCode.CLASE_NAME_NOT_NULL_ERROR);
        }
        if(checkTeacher(updateClaseVo.getTeacherId(),updateClaseVo.getClaseId())){
            throw new BizException(ErrorCode.TEACHER_POSITION_REPEAT_ERROR);
        }
        Clase clase = new Clase();
        BeanUtil.copyProperties(updateClaseVo,clase);
        updateClaseVo.setUpdateTime(LocalDateTime.now());
        return claseMapper.updateById(clase);
    }

    /**
     * 增加班级
     *
     * @param insertClaseVo
     */
    @Override
    public void addClase(InsertClaseVo insertClaseVo, UserJwtVo currentUser) {
        if(checkName(insertClaseVo.getClaseName(),insertClaseVo.getClaseId())){
            throw new BizException(ErrorCode.CLASE_NAME_NOT_NULL_ERROR);
        }
        if(checkTeacher(insertClaseVo.getTeacherId(),insertClaseVo.getClaseId())){
            throw new BizException(ErrorCode.TEACHER_POSITION_REPEAT_ERROR);
        }
        Clase clase = new Clase();
        BeanUtil.copyProperties(insertClaseVo,clase);
        clase.setCreateTime(LocalDateTime.now());
        clase.setSchoolId(currentUser.getSchoolId());
        clase.setCreator(currentUser.getId());
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
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("clase_id",claseId);
        studentQueryWrapper.eq("is_del",true);
        List<Student> students = studentMapper.selectList(studentQueryWrapper);
        if(students.size()>0){
            throw new BizException(ErrorCode.CLASE_STRDENT_NOT_NULL_ERROR);
        }
        clase.setIsDel(false);
        clase.setUpdateTime(LocalDateTime.now());
        claseMapper.updateById(clase);
    }


    //判断重名
    public boolean checkName(String claseName,String claseId){
        QueryWrapper<Clase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("clase_name",claseName);
        if (StringUtils.isNotEmpty(claseId)){
            queryWrapper.ne("clase_id",claseId);
        }
        queryWrapper.eq("is_del",true);
        Clase clase = claseMapper.selectOne(queryWrapper);
        if (clase == null ){
            return false;
        }
        return true;
    }

    //判断教师是否有同级任职
    public boolean checkTeacher(String teacherId,String claseId){
        QueryWrapper<Clase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherId);
        if (StringUtils.isNotEmpty(claseId)){
            queryWrapper.ne("clase_id",claseId);
        }
        queryWrapper.eq("is_del",true);
        Clase clase = claseMapper.selectOne(queryWrapper);
        if (clase == null ){
            return false;
        }
        return true;
    }
}
