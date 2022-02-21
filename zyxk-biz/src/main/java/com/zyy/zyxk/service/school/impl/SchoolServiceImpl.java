package com.zyy.zyxk.service.school.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.school.SchoolVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.util.BeanUtil;
import com.zyy.zyxk.dao.SchoolMapper;
import com.zyy.zyxk.dao.SysAuthorityMapper;
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

    @Override
    public void add(SchoolVo schoolVo, UserJwtVo currentUser) {
        //判断是否有权限新增学校
        if(!checkAuthority(currentUser.getId())){
            throw new BizException(ErrorCode.NO_AUTH);
        }
        //判断学校名是否有重复
        QueryWrapper<School> schoolQueryWrapper = new QueryWrapper<>();
        schoolQueryWrapper.eq("school_name",schoolVo.getSchoolName());
        schoolQueryWrapper.eq("is_valid",true);
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

    }
}
