package com.zyy.zyxk.service.role.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.view.SysRoleAuthorityView;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.role.RoleAuthorityGroupVo;
import com.zyy.zyxk.api.vo.role.RoleAuthorityListVo;
import com.zyy.zyxk.api.vo.role.RoleAuthorityVo;
import com.zyy.zyxk.api.vo.role.RoleVo;
import com.zyy.zyxk.common.constant.CommonEnum;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.util.BeanUtil;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.dao.RoleAuthorityRelMapper;
import com.zyy.zyxk.dao.RoleMapper;
import com.zyy.zyxk.dao.SysAuthorityMapper;
import com.zyy.zyxk.dao.entity.Role;
import com.zyy.zyxk.dao.entity.RoleAuthorityRel;
import com.zyy.zyxk.dao.entity.SysAuthority;
import com.zyy.zyxk.service.common.CommonService;
import com.zyy.zyxk.service.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/14/22 2:46 PM
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleAuthorityRelMapper roleAuthorityRelMapper;

    @Autowired
    private SysAuthorityMapper sysAuthorityMapper;

    @Autowired
    private CommonService commonService;

    @Override
    @Transactional
    public void addRole(RoleVo roleVo, UserJwtVo currentUser) {
        //如果有重名抛出
        if(roleName(roleVo.getRoleName(),null)){
            throw new BizException(ErrorCode.Role_Name_Existed);
        }
        //设置信息新增
        Role role = new Role();
        role.setRoleId(commonService.getSequence("ROLE",null));
        role.setRoleName(roleVo.getRoleName());
        role.setRoleComment(roleVo.getRoleComment());
        role.setCreator(currentUser.getId());
        role.setCreateTime(LocalDateTime.now());
        role.setIsDel(true);
        roleMapper.insert(role);
        //插入角色之后将所有权限赋给该角色但是是未启用状态
        QueryWrapper<SysAuthority> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del",true);
        List<SysAuthority> authorities=sysAuthorityMapper.selectList(queryWrapper);
        List<RoleAuthorityRel> roleAuthorities=new ArrayList<>();
        for (SysAuthority authority:authorities) {
            RoleAuthorityRel roleAuthority=new RoleAuthorityRel();
            roleAuthority.setAuthorityId(authority.getAuthorityId());
            roleAuthority.setRoleId(role.getRoleId());
            roleAuthority.setCreator(currentUser.getId());
            roleAuthority.setCreateTime(LocalDateTime.now());
            roleAuthorityRelMapper.insert(roleAuthority);
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
        role.setIsDel(false);
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateById(role);
    }

    @Override
    @Transactional
    public void updateRole(RoleAuthorityListVo roleAuthorityVo, UserJwtVo currentUser) {
        //查询是否有这个角色
        Role role = roleMapper.selectById(roleAuthorityVo.getRoleId());
        if(role==null){
            throw new BizException(ErrorCode.Role_Id_Invalid);
        }
        if(roleName(roleAuthorityVo.getRoleName(),roleAuthorityVo.getRoleId())){
            throw new BizException(ErrorCode.Role_Name_Existed);
        }
        //跟新角色信息
        BeanUtil.copyProperties(roleAuthorityVo,role);
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateById(role);

        List<RoleAuthorityGroupVo> authorities =roleAuthorityVo.getAuthoritys();
        List<RoleAuthorityRel> roleAuthorityRels=new ArrayList<>();
        for (RoleAuthorityGroupVo menu:authorities) {
            if(menu.getMenu().getAuthorityType()!=1) {
                //添加菜单权限选择
                RoleAuthorityRel roleAuthority = new RoleAuthorityRel();
                roleAuthority.setRoleAuthorityRelId(menu.getMenu().getRoleAuthorityRelId());
                roleAuthority.setIsDel(menu.getMenu().getIsEnable());
                roleAuthorityRels.add(roleAuthority);

                //添加按钮权限选择
                List<RoleAuthorityVo> roleAuthorityVos = menu.getAuthorities();
                for (RoleAuthorityVo roleAuthorityVo1 : roleAuthorityVos) {
                    roleAuthorityVo1 = new RoleAuthorityVo();
                    roleAuthorityVo1.setRoleAuthorityRelId((roleAuthorityVo1.getRoleAuthorityRelId()));
                    roleAuthorityVo1.setIsEnable(roleAuthorityVo1.getIsEnable());
                    roleAuthorityRels.add(roleAuthority);
                }
            }
        }

        QueryWrapper<RoleAuthorityRel> wrapper=new QueryWrapper<>();
        wrapper.eq("role_id",roleAuthorityVo.getRoleId());
        List<RoleAuthorityRel> resultDoList=roleAuthorityRelMapper.selectList(wrapper);

        //赋值 resultDoList
        for (RoleAuthorityRel roleAuthority:resultDoList) {
            RoleAuthorityRel sourceRoleAuthority=roleAuthorityRels.stream().filter(roleAuthorityRel -> roleAuthorityRel.getRoleAuthorityRelId().equals(roleAuthority.getRoleAuthorityRelId())).findFirst().get();
            roleAuthority.setIsDel(sourceRoleAuthority.getIsDel());
            roleAuthorityRelMapper.insertRoleAuthority(roleAuthority.getRoleAuthorityRelId(),roleAuthority.getRoleId(),roleAuthority.getAuthorityId(),roleAuthority.getCreator(),roleAuthority.getIsDel());
        }

    }

    public boolean roleName(String roleName, String roleId){
        //查询是否有重名
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("role_name",roleName);
        if(StringUtils.isEmpty(roleId)) {
            roleQueryWrapper.ne("role_id", roleId);
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
    public IPage<RoleVo> getList(IPage page, String selectStringKey) {
        return roleMapper.getList(page,selectStringKey);
    }



    @Override
    public Response getAuthorityList(String roleId) {
            List<SysRoleAuthorityView> roleAuthorities = roleAuthorityRelMapper.getList(roleId);

            List<SysRoleAuthorityView> menus = roleAuthorities.stream().filter(sysRoleAuthority -> sysRoleAuthority.getAuthorityType() == CommonEnum.AuthorityType.Menu.getValue()).collect(Collectors.toList());
            List<RoleAuthorityGroupVo> resultVo = new ArrayList<>();
            for (SysRoleAuthorityView menu : menus) {
                //获取菜单
                RoleAuthorityGroupVo roleAuthorityGroupVo = new RoleAuthorityGroupVo();
                RoleAuthorityVo roleAuthorityVo = new RoleAuthorityVo();

                //赋值菜单vo
                BeanUtil.copyProperties(menu, roleAuthorityVo);

                roleAuthorityGroupVo.setMenu(roleAuthorityVo);

                //获取权限列表
                List<SysRoleAuthorityView> buttons = new ArrayList<>();
                for (SysRoleAuthorityView s : roleAuthorities) {
                    if (s.getParentAuthorityId() != null) {
                        if (s.getParentAuthorityId().equals(menu.getAuthorityId())) {
                            buttons.add(s);
                        }
                    }
                }
                //赋值权限vo列表
                List<RoleAuthorityVo> roleAuthorityVos = BeanUtil.copyListProperties(buttons, RoleAuthorityVo.class);
                roleAuthorityGroupVo.setAuthorities(roleAuthorityVos);

                //vo 添加到result list
                resultVo.add(roleAuthorityGroupVo);
            }
            if (roleAuthorities != null) {
                return Response.success("角色权限列表获取成功", resultVo);
            }


        return Response.fail(ErrorCode.Role_Auth_List_Get_Error);
    }
}
