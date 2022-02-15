package com.zyy.zyxk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.RoleAuthorityVo;
import com.zyy.zyxk.api.vo.RoleVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.util.BeanUtil;
import com.zyy.zyxk.dao.RoleAuthorityRelMapper;
import com.zyy.zyxk.dao.RoleMapper;
import com.zyy.zyxk.dao.entity.Role;
import com.zyy.zyxk.dao.entity.RoleAuthorityRel;
import com.zyy.zyxk.service.CommonService;
import com.zyy.zyxk.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/14/22 2:46 PM
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private RoleAuthorityRelMapper roleAuthorityRelMapper;

    @Override
    @Transactional
    public void addRole(RoleAuthorityVo roleAuthorityVo, UserJwtVo currentUser) {
        //如果有重名抛出
        if(roleName(roleAuthorityVo)){
            throw new BizException(ErrorCode.Role_Name_Existed);
        }
        //设置信息新增
        Role role = new Role();
        role.setRoleId(commonService.getSequence("ROLE",null));
        role.setRoleName(roleAuthorityVo.getRoleName());
        role.setCreator(currentUser.getId());
        role.setCreateTime(LocalDateTime.now());
        role.setDel(true);
        roleMapper.insert(role);
        //遍历权限Id设置信息和角色关联
        for(String authorityId : roleAuthorityVo.getAuthoritys()){
            RoleAuthorityRel roleAuthorityRel = new RoleAuthorityRel();
            roleAuthorityRel.setRoleId(role.getRoleId());
            roleAuthorityRel.setAuthorityId(authorityId);
            roleAuthorityRel.setRoleAuthorityRelId(commonService.getSequence("ROLE_AUTHORITY_REL",null));
            roleAuthorityRel.setCreateTime(LocalDateTime.now());
            roleAuthorityRel.setCreator(currentUser.getId());
            roleAuthorityRelMapper.insert(roleAuthorityRel);
        }
    }

    @Override
    @Transactional
    public void delRole(String roleId) {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("role_id",roleId);
        roleQueryWrapper.eq("is_del",true);
        Role role = roleMapper.selectOne(roleQueryWrapper);
        if(role == null){
            throw new BizException(ErrorCode.Role_Id_Invalid);
        }
        role.setDel(false);
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateById(role);
    }

    @Override
    public void updateRole(RoleAuthorityVo roleAuthorityVo, UserJwtVo currentUser) {
        //查询是否有这个角色
        Role role = roleMapper.selectById(roleAuthorityVo.getRoleId());
        if(role==null){
            throw new BizException(ErrorCode.Role_Id_Invalid);
        }
        if(roleName(roleAuthorityVo)){
            throw new BizException(ErrorCode.Role_Name_Existed);
        }
        //跟新角色信息
        BeanUtil.copyProperties(roleAuthorityVo,role);
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateById(role);
        //先删除之前的所有绑定关系
        roleAuthorityRelMapper.delAllRoleAuthority(roleAuthorityVo.getRoleId());
        //插入新的绑定关系
        for(String authorityId : roleAuthorityVo.getAuthoritys()){
            RoleAuthorityRel roleAuthorityRel = new RoleAuthorityRel();
            roleAuthorityRel.setRoleAuthorityRelId(commonService.getSequence("ROLE_AUTHORITY_REL",null));
            roleAuthorityRel.setRoleId(role.getRoleId());
            roleAuthorityRel.setAuthorityId(authorityId);
            roleAuthorityRel.setCreator(currentUser.getId());
            roleAuthorityRel.setCreateTime(LocalDateTime.now());
            roleAuthorityRelMapper.insert(roleAuthorityRel);
        }

    }

    public boolean roleName(RoleAuthorityVo roleAuthorityVo){
        //查询是否有重名
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("role_name",roleAuthorityVo.getRoleName());
        if(StringUtils.isEmpty(roleAuthorityVo.getRoleId())) {
            roleQueryWrapper.ne("role_id", roleAuthorityVo.getRoleId());
        }
        roleQueryWrapper.eq("is_del",true);
        Role role = roleMapper.selectOne(roleQueryWrapper);
        //如果有重名抛出
        if(role != null){
            return true;
        }
        return false;
    }

    @Override
    public List<RoleVo> getList() {
        return roleMapper.getList();
    }
}
