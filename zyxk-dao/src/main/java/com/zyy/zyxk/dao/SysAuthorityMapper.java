package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyy.zyxk.api.vo.AuthorityVo;
import com.zyy.zyxk.dao.entity.SysAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/25/22 4:46 PM
 */
public interface SysAuthorityMapper extends BaseMapper<SysAuthority> {

    List<AuthorityVo> getList(IPage page,@Param("selectKey") String selectKey);

    List<String> getUserAuthorityAll(@Param("id") String id);
}
