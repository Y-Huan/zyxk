package com.zyy.zyxk.service.college.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.college.CollegeListVo;
import com.zyy.zyxk.api.vo.college.InsertCollegeVo;
import com.zyy.zyxk.api.vo.college.SelectCollegeVo;
import com.zyy.zyxk.api.vo.college.UpdateCollegeVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.dao.CollegeMapper;
import com.zyy.zyxk.dao.entity.College;
import com.zyy.zyxk.service.college.CollegeService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fl
 * @date 2022-02-28
 * 分院
 **/
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService {

    @Resource
    private CollegeMapper collegeMapper;


    /**
     * 分院列表
     *
     * @param selectCollegeVo
     * @return
     */
    @Override
    public List<CollegeListVo> selectCollegeList(SelectCollegeVo selectCollegeVo) {
        return collegeMapper.selectCollegeList(selectCollegeVo);
    }

    /**
     * 分院详情
     *
     * @param collegeId
     * @return
     */
    @Override
    public CollegeListVo selectCollegeById(String collegeId) {
        return collegeMapper.selectCollegeById(collegeId);
    }

    /**
     * 增加分院
     *
     * @param insertCollegeVo
     */
    @Override
    public void insertCollege(InsertCollegeVo insertCollegeVo, UserJwtVo currentUser) {
            College college = new College();
            college.setCollegeId(insertCollegeVo.getCollegeId());
            college.setCollegeName(insertCollegeVo.getCollegeName());
            college.setCreateTime(LocalDateTime.now());
            college.setIsDel(true);
            college.setDean(insertCollegeVo.getDean());
            college.setSchoolId(insertCollegeVo.getSchoolId());
            college.setCreator(currentUser.getUserName());
            collegeMapper.insert(college);
    }

    /**
     * 修改分院
     *
     * @param updateCollegeVo
     */
    @Override
    public void updateCollege(UpdateCollegeVo updateCollegeVo, UserJwtVo currentUser) {
        updateCollegeVo.setCreator(currentUser.getUserName());
        updateCollegeVo.setUpdateTime(LocalDateTime.now());
        collegeMapper.updateCollege(updateCollegeVo);
    }

    /**
     * 删除分院
     *
     * @param collegeId
     */
    @Override
    public void deleteCollege(String collegeId) {
        QueryWrapper<College> collegeQueryWrapper = new QueryWrapper<>();
        collegeQueryWrapper.eq("college_id",collegeId);
        collegeQueryWrapper.eq("is_del",true);
        College college= collegeMapper.selectOne(collegeQueryWrapper);
        if(college == null){
            throw new BizException(ErrorCode.Clase_Id_Invalid);
        }
        college.setIsDel(false);
        college.setUpdateTime(LocalDateTime.now());
        collegeMapper.updateById(college);
    }
}
