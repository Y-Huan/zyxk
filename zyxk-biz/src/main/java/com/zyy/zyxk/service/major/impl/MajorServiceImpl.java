package com.zyy.zyxk.service.major.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.major.InsertMajorVo;
import com.zyy.zyxk.api.vo.major.MajorListVo;
import com.zyy.zyxk.api.vo.major.SelectMajorVo;
import com.zyy.zyxk.api.vo.major.UpdateMajorVo;
import com.zyy.zyxk.dao.MajorMapper;
import com.zyy.zyxk.dao.entity.Major;
import com.zyy.zyxk.service.major.MajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public void addMajor(InsertMajorVo insertMajorVo) {

    }

    /**
     * 删除专业
     *
     * @param majorId
     */
    @Override
    public void deleteMajor(String majorId) {

    }

    /**
     * 修改专业
     *
     * @param updateMajorVo
     */
    @Override
    public void updateMajor(UpdateMajorVo updateMajorVo) {

    }
}
