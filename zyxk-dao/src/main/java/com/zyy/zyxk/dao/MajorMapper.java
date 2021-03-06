package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyy.zyxk.api.vo.major.MajorListVo;
import com.zyy.zyxk.api.vo.major.UpdateMajorVo;
import com.zyy.zyxk.dao.entity.Major;
import org.apache.ibatis.annotations.Param;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 4:46 PM
 */
public interface MajorMapper extends BaseMapper<Major> {

    /**
     * 专业列表
     * @return
     */
    IPage<MajorListVo>selectMajorList(IPage<MajorListVo> page,
                                      @Param("majorName")String majorName,
                                      @Param("personInChargeName")String personInChargeName,
                                      @Param("phone")String phone
                                        );

    /**
     * 专业详情
     * @param majorId
     * @return
     */
    MajorListVo selectedMajorById(@Param("majorId")String majorId);

    /**
     * 修改专业
     * @param updateMajorVo
     * @return
     */
    int updateMajor(UpdateMajorVo updateMajorVo);

}
