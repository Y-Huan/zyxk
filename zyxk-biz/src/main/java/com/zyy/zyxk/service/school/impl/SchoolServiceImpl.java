package com.zyy.zyxk.service.school.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.school.SchoolVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.util.BeanUtil;
import com.zyy.zyxk.dao.CollegeMapper;
import com.zyy.zyxk.dao.SchoolMapper;
import com.zyy.zyxk.dao.SysAuthorityMapper;
import com.zyy.zyxk.dao.entity.College;
import com.zyy.zyxk.dao.entity.School;
import com.zyy.zyxk.service.school.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/18/22 4:46 PM
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {

    @Autowired
    private SysAuthorityMapper sysAuthorityMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public void add(SchoolVo schoolVo, UserJwtVo currentUser) {
        //判断是否有权限新增学校   权限判断暂缓开发
//        if(!checkAuthority(currentUser.getId())){
//            throw new BizException(ErrorCode.NO_AUTH);
//        }
        //判断学校名是否有重复
        QueryWrapper<School> schoolQueryWrapper = new QueryWrapper<>();
        schoolQueryWrapper.eq("school_name",schoolVo.getSchoolName());
        schoolQueryWrapper.eq("is_del",true);
        School checkSchool = schoolMapper.selectOne(schoolQueryWrapper);
        if(checkSchool!=null){
            throw new BizException(ErrorCode.SCHOOOL_NAME_NOT_NULL);
        }
        School school = new School();
        BeanUtil.copyProperties(schoolVo,school);
        school.setCreator(currentUser.getId());
        school.setCreateTime(LocalDateTime.now());
        schoolMapper.insert(school);
    }

    public boolean checkAuthority(String id){
        //查询用户所有权限
        List<String> authorityAll = sysAuthorityMapper.getUserAuthorityAll(id);
        for(String authority:authorityAll){
            if("addSchool".equals(authority)){
                return true;
            }
        }
        return false;
    }


    @Override
    public void delSchool(String schoolId, UserJwtVo currentUser) {
        //鉴权暂缓开发

        //判断学校ID的学校是否有效存在
        School school = schoolMapper.selectById(schoolId);
        if(school==null){
            throw new BizException(ErrorCode.SCHOOL_NULL_ERROR);
        }
        //判断学校下是否有分院如果有分院就不能删除需要删除分院之后才能删除
        QueryWrapper<College> collegeQueryWrapper = new QueryWrapper<>();
        collegeQueryWrapper.eq("school_id",schoolId);
        collegeQueryWrapper.eq("is_del",true);
        List<College> colleges = collegeMapper.selectList(collegeQueryWrapper);
        if (colleges.size()>0){
            throw new BizException(ErrorCode.SCHOOL_COLLEGE_NOT_NULL_ERROR);
        }
        //
        school.setIsDel(false);
        school.setUpdateTime(LocalDateTime.now());
        schoolMapper.updateById(school);

    }

    @Override
    public IPage<SchoolVo> getList(IPage<SchoolVo> page,String selectStringKey, UserJwtVo currentUser) {
        return schoolMapper.getList(page,selectStringKey);
    }

    @Override
    public SchoolVo detail(String schoolId) {
        SchoolVo schoolVo = schoolMapper.getDetail(schoolId);
        return schoolVo;
    }

    @Override
    public void updateSchool(SchoolVo schoolVo) {
        //鉴权暂缓开发

        //判断学校ID的学校是否有效存在
        School school = schoolMapper.selectById(schoolVo.getSchoolId());
        if(school==null){
            throw new BizException(ErrorCode.SCHOOL_NULL_ERROR);
        }
        //判断更新后学校名是否有重复
        QueryWrapper<School> schoolQueryWrapper = new QueryWrapper<>();
        schoolQueryWrapper.eq("school_name",schoolVo.getSchoolName());
        schoolQueryWrapper.ne("school_id",schoolVo.getSchoolId());
        schoolQueryWrapper.eq("is_del",true);
        School checkSchool = schoolMapper.selectOne(schoolQueryWrapper);
        if(checkSchool!=null){
            throw new BizException(ErrorCode.SCHOOOL_NAME_NOT_NULL);
        }
        BeanUtil.copyProperties(schoolVo,school);
        school.setUpdateTime(LocalDateTime.now());
        schoolMapper.updateById(school);
    }
}
