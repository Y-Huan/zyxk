package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyy.zyxk.api.vo.college.CollegeListVo;
import com.zyy.zyxk.api.vo.college.SelectCollegeVo;
import com.zyy.zyxk.api.vo.college.UpdateCollegeVo;
import com.zyy.zyxk.dao.entity.College;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 4:42 PM
 */
public interface CollegeMapper extends BaseMapper<College> {

    /**
     * 分院列表
     * @param selectCollegeVo
     * @return
     */
    List<CollegeListVo> selectCollegeList(SelectCollegeVo selectCollegeVo);

    /**
     * 分院详情
     * @param collegeId
     * @return
     */
    CollegeListVo selectCollegeById(@Param("collegeId")String collegeId);

    /**
     * 修改分院
     * @param updateCollegeVo
     * @return
     */
    int updateCollege(UpdateCollegeVo updateCollegeVo);

}
