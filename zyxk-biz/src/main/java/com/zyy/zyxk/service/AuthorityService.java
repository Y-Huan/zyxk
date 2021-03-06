package com.zyy.zyxk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.api.vo.AuthorityVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.selectVo.BaseSelectVo;
import com.zyy.zyxk.dao.entity.SysAuthority;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/14/22 8:59 AM
 */

public interface AuthorityService  extends IService<SysAuthority> {
    //新增权限
    void addAuthority(AuthorityVo authorityVo, UserJwtVo currentUser);
    //删除权限
    void delAuthority(String authorityId);
    //修改权限
    void updateAuthority(AuthorityVo authorityVo);
    //权限列表
    IPage<AuthorityVo> getList(IPage page, BaseSelectVo baseSelectVo);
    //权限信息
    SysAuthority deatil(String authorityId);
}
