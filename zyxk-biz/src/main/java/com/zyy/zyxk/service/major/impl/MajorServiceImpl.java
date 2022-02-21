package com.zyy.zyxk.service.major.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.major.InsertMajorVo;
import com.zyy.zyxk.api.vo.major.MajorListVo;
import com.zyy.zyxk.api.vo.major.SelectMajorVo;
import com.zyy.zyxk.api.vo.major.UpdateMajorVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.dao.MajorMapper;
import com.zyy.zyxk.dao.entity.Major;
import com.zyy.zyxk.service.major.MajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fl
 * @date 2022-02-15
 * 专业
 **/



@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {

    @Resource
    private MajorMapper majorMapper;


    /**
     * 专业列表
     *
     * @param selectMajorVo
     * @return
     */
    @Override
    public List<MajorListVo> selectMajorList(SelectMajorVo selectMajorVo) {
        return majorMapper.selectMajorList(selectMajorVo);
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
            updateMajorVo.setUpdateTime(LocalDateTime.now());
            updateMajorVo.setCreator(currentUser.getUserName());
            majorMapper.updateMajor(updateMajorVo);
    }
}
