package com.zyy.zyxk.service.major.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.major.InsertMajorVo;
import com.zyy.zyxk.api.vo.major.MajorListVo;
import com.zyy.zyxk.api.vo.major.SelectMajorVo;
import com.zyy.zyxk.api.vo.major.UpdateMajorVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.util.BeanUtil;
import com.zyy.zyxk.dao.ClaseMapper;
import com.zyy.zyxk.dao.MajorMapper;
import com.zyy.zyxk.dao.entity.Clase;
import com.zyy.zyxk.dao.entity.Major;
import com.zyy.zyxk.service.major.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fl
 * @date 2022-02-15
 * 专业
 **/



@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private ClaseMapper claseMapper;


    /**
     * 专业列表
     *
     * @param selectMajorVo
     * @return
     */
    @Override
    public IPage<MajorListVo> selectMajorList(IPage<MajorListVo> page,SelectMajorVo selectMajorVo) {
        return majorMapper.selectMajorList(page,selectMajorVo.getMajorName(),selectMajorVo.getPersonInChargeName(),selectMajorVo.getPhone());
    }

    /**
     * 专业详情
     *
     * @param majorId
     * @return
     */
    @Override
    public MajorListVo selectedMajorById(String majorId) {
        return majorMapper.selectedMajorById(majorId);
    }

    /**
     * 增加专业
     *
     * @param insertMajorVo
     */
    @Override
    public void addMajor(InsertMajorVo insertMajorVo, UserJwtVo currentUser) {
        //判断教师是否有同级职位
        QueryWrapper<Major> teacherqueryWrapper = new QueryWrapper<>();
        teacherqueryWrapper.eq("person_in_charge_id",insertMajorVo.getPersonInChargeId());
        teacherqueryWrapper.eq("is_del",true);
        Major selectMajor1 = majorMapper.selectOne(teacherqueryWrapper);
        if(selectMajor1!=null){
            throw new BizException(ErrorCode.TEACHER_POSITION_REPEAT_ERROR);
        }
        QueryWrapper<Major> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("major_name",insertMajorVo.getMajorName());
        queryWrapper.eq("is_del",true);
        Major selectOne = majorMapper.selectOne(queryWrapper);
        if(selectOne !=null){
            throw new BizException(ErrorCode.Major_Name_existed);
        }


        Major major = new Major();
        major.setIsDel(true);
        major.setCreator(currentUser.getId());
        major.setCreateTime(LocalDateTime.now());
        major.setMajorName(insertMajorVo.getMajorName());
        major.setCollegeId(insertMajorVo.getCollegeId());
        major.setPersonInChargeId(insertMajorVo.getPersonInChargeId());
        majorMapper.insert(major);
    }

    /**
     * 删除专业
     *
     * @param majorId
     */
    @Override
    public void deleteMajor(String majorId) {
        QueryWrapper<Major> majorQueryWrapper = new QueryWrapper<>();
        majorQueryWrapper.eq("major_id",majorId);
        majorQueryWrapper.eq("is_del",true);
        Major major= majorMapper.selectOne(majorQueryWrapper);
        if(major == null){
            throw new BizException(ErrorCode.Major_Id_Invalid);
        }
        //判断专业下是否有未删除的班级
        QueryWrapper<Clase> claseQueryWrapper = new QueryWrapper<>();
        claseQueryWrapper.eq("major_id",majorId);
        claseQueryWrapper.eq("is_del",true);
        List<Clase> clase= claseMapper.selectList(claseQueryWrapper);
        if(clase.size()>0){
            throw new BizException(ErrorCode.MAJOR_CLASE_NOT_NULL_ERROR);
        }
        major.setIsDel(false);
        major.setUpdateTime(LocalDateTime.now());
        majorMapper.updateById(major);
    }

    /**
     * 修改专业
     *
     * @param updateMajorVo
     */
    @Override
    public void updateMajor(UpdateMajorVo updateMajorVo, UserJwtVo currentUser) {
        //判断教师是否有同级职位
        QueryWrapper<Major> teacherqueryWrapper = new QueryWrapper<>();
        teacherqueryWrapper.eq("person_in_charge_id",updateMajorVo.getPersonInChargeId());
        teacherqueryWrapper.ne("major_id",updateMajorVo.getMajorId());
        teacherqueryWrapper.eq("is_del",true);
        Major selectMajor1 = majorMapper.selectOne(teacherqueryWrapper);
        if(selectMajor1!=null){
            throw new BizException(ErrorCode.TEACHER_POSITION_REPEAT_ERROR);
        }
        QueryWrapper<Major> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("major_name",updateMajorVo.getMajorName());
        queryWrapper.ne("major_id",updateMajorVo.getMajorId());
        queryWrapper.eq("is_del",true);
        Major selectOne = majorMapper.selectOne(queryWrapper);
        if(selectOne !=null){
            throw new BizException(ErrorCode.Major_Name_existed);
        }
        Major major = new Major();
        BeanUtil.copyProperties(updateMajorVo,major);
        updateMajorVo.setUpdateTime(LocalDateTime.now());
        majorMapper.updateById(major);
    }
}
