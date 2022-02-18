package com.zyy.zyxk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.AuthorityVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.util.BeanUtil;
import com.zyy.zyxk.dao.SysAuthorityMapper;
import com.zyy.zyxk.dao.entity.SysAuthority;
import com.zyy.zyxk.service.AuthorityService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/14/22 9:01 AM
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<SysAuthorityMapper, SysAuthority> implements AuthorityService {

    @Autowired
    private SysAuthorityMapper sysAuthorityMapper;


    @Override
    @Transactional
    public void addAuthority(AuthorityVo authorityVo, UserJwtVo currentUser) {
        //判断名称是否存在
        if(authorityName(authorityVo)){
            throw new BizException(ErrorCode.AUTHORITY_NAME_ERROR);
        }
        SysAuthority sysAuthority = new SysAuthority();
        //将传入的信息映射到实体
        BeanUtil.copyProperties(authorityVo,sysAuthority);
        //设置更新时间
        sysAuthority.setCreateTime(LocalDateTime.now());
        //设置创建人
        sysAuthority.setCreator(currentUser.getId());
        //插入数据
        sysAuthorityMapper.insert(sysAuthority);
    }

    @Override
    @Transactional
    public void delAuthority(String authorityId) {
        //根据ID查询权限
        SysAuthority sysAuthority = sysAuthorityMapper.selectById(authorityId);
        //判空
        if(sysAuthority == null ){
            throw new BizException(ErrorCode.BIND_ERROR);
        }
        //设置无效
        sysAuthority.setIsDel(false);
        //跟新数据
        sysAuthorityMapper.updateById(sysAuthority);
    }

    @Override
    @Transactional
    public void updateAuthority(AuthorityVo authorityVo) {
        //根据ID查询权限
        SysAuthority sysAuthority = sysAuthorityMapper.selectById(authorityVo.getAuthorityId());
        //判空
        if(sysAuthority == null ){
            throw new BizException(ErrorCode.AUTHORITY_NULL);
        }
        if(authorityName(authorityVo)){
            throw new BizException(ErrorCode.AUTHORITY_NAME_ERROR);
        }
        //将新数据映射到实体
        BeanUtil.copyProperties(authorityVo,sysAuthority);
        //设置更新时间
        sysAuthority.setUpdateTime(LocalDateTime.now());
        //跟新数据
        sysAuthorityMapper.updateById(sysAuthority);
    }

    @Override
    public List<AuthorityVo> getList(IPage page, BaseSelectVo baseSelectVo) {
        //查询信息返回
        return sysAuthorityMapper.getList(page,baseSelectVo.getSelectStringKey());
    }

    public boolean authorityName(AuthorityVo authorityVo){
        //判断是否有重名
        QueryWrapper<SysAuthority> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("authority_name",authorityVo.getAuthorityName());
        if(StringUtils.isNotEmpty(authorityVo.getAuthorityId())){
            queryWrapper.ne("authority_id",authorityVo.getAuthorityId());
        }
        queryWrapper.eq("is_del",true);
        SysAuthority sysAuthority = sysAuthorityMapper.selectOne(queryWrapper);
        if(sysAuthority !=null ){
            return true;
        }
        return false;
    }
}
