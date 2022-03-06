package com.zyy.zyxk.service.college.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.college.CollegeListVo;
import com.zyy.zyxk.api.vo.college.InsertCollegeVo;
import com.zyy.zyxk.api.vo.college.UpdateCollegeVo;
import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.util.BeanUtil;
import com.zyy.zyxk.dao.CollegeMapper;
import com.zyy.zyxk.dao.MajorMapper;
import com.zyy.zyxk.dao.entity.College;
import com.zyy.zyxk.dao.entity.Major;
import com.zyy.zyxk.service.college.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fl
 * @date 2022-02-28
 * 分院
 **/
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private MajorMapper majorMapper;



    /**
     * 分院列表
     *
     * @return
     */
    @Override
    public IPage<CollegeListVo> selectCollegeList(IPage<CollegeListVo> page, BaseSelectVo baseSelectVo,String schoolId) {
        return collegeMapper.selectCollegeList(page,baseSelectVo.getSelectStringKey(),schoolId);
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
    @Transactional
    public void insertCollege(InsertCollegeVo insertCollegeVo, UserJwtVo currentUser) {
        //判断教师是否有同级职位
        QueryWrapper<College> teacherqueryWrapper = new QueryWrapper<>();
        teacherqueryWrapper.eq("dean",insertCollegeVo.getDean());
        teacherqueryWrapper.eq("is_del",true);
        College selectCollege1 = collegeMapper.selectOne(teacherqueryWrapper);
        if(selectCollege1!=null){
            throw new BizException(ErrorCode.TEACHER_POSITION_REPEAT_ERROR);
        }
        //判断是否重名
        QueryWrapper<College> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("college_name",insertCollegeVo.getCollegeName());
        queryWrapper.eq("is_del",true);
        College selectCollege = collegeMapper.selectOne(queryWrapper);
        if(selectCollege!=null){
            throw new BizException(ErrorCode.COLLEGE_NAME_NOT_NULL_ERROR);
        }

        College college = new College();
        BeanUtil.copyProperties(insertCollegeVo,college);
        college.setCreator(currentUser.getId());
        college.setSchoolId(currentUser.getSchoolId());
        college.setCreateTime(LocalDateTime.now());
        collegeMapper.insert(college);
    }

    /**
     * 修改分院
     *
     * @param updateCollegeVo
     */
    @Override
    public void updateCollege(UpdateCollegeVo updateCollegeVo, UserJwtVo currentUser) {
        //判断教师是否有同级职位
        QueryWrapper<College> teacherqueryWrapper = new QueryWrapper<>();
        teacherqueryWrapper.eq("dean",updateCollegeVo.getDean());
        teacherqueryWrapper.ne("college_id",updateCollegeVo.getCollegeId());
        teacherqueryWrapper.eq("is_del",true);
        College selectCollege1 = collegeMapper.selectOne(teacherqueryWrapper);
        if(selectCollege1!=null){
            throw new BizException(ErrorCode.TEACHER_POSITION_REPEAT_ERROR);
        }
        //判断修改后是否重名
        QueryWrapper<College> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("college_name",updateCollegeVo.getCollegeName());
        queryWrapper.ne("college_id",updateCollegeVo.getCollegeId());
        queryWrapper.eq("is_del",true);
        College selectCollege = collegeMapper.selectOne(queryWrapper);
        if(selectCollege!=null){
            throw new BizException(ErrorCode.COLLEGE_NAME_NOT_NULL_ERROR);
        }
        College college = new College();
        BeanUtil.copyProperties(updateCollegeVo,college);
        college.setUpdateTime(LocalDateTime.now());
        collegeMapper.updateById(college);
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
        QueryWrapper<Major> majorQueryWrapper = new QueryWrapper<>();
        majorQueryWrapper.eq("college_id",collegeId);
        majorQueryWrapper.eq("is_del",true);
        List<Major> majors = majorMapper.selectList(majorQueryWrapper);
        if(majors.size()>0){
            throw new BizException(ErrorCode.COLLEGE_MAJOR_NOT_NULL_ERROR);
        }
        college.setIsDel(false);
        college.setUpdateTime(LocalDateTime.now());
        collegeMapper.updateById(college);
    }
}
