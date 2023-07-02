package com.device.mbg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.device.common.utils.StringUtil;
import com.device.mbg.domain.entity.Menu;
import com.device.mbg.domain.entity.Role;
import com.device.mbg.domain.entity.RoleMenu;
import com.device.mbg.mapper.MenuMapper;
import com.device.mbg.mapper.RoleMapper;
import com.device.mbg.mapper.RoleMenuMapper;
import com.device.mbg.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色菜单关系表 服务实现类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Menu> getMenuListByUserId(String userId) {
        if (StringUtil.isBlank(userId)) {
            return null;
        }
        return menuMapper.getMenuList(userId);
    }

    @Override
    public List<Role> getRoleListByUserId(String userId) {
        if (StringUtil.isBlank(userId)) {
            return null;
        }
        return roleMapper.getRoleList(userId);
    }

    @Override
    public List<Menu> getMenuListByRoleId(String roleId) {
        if (StringUtil.isBlank(roleId)) {
            return null;
        }
        return baseMapper.getMenuListByRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bind(String roleId, List<String> menuIds) {
        // 先删除所有当前role绑定菜单
        baseMapper.delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
        // 绑定菜单
        List<RoleMenu> roleMenuList = new ArrayList<>();
        menuIds.forEach(menuId -> roleMenuList.add(RoleMenu.builder().roleId(roleId).menuId(menuId).build()));
        this.saveBatch(roleMenuList);
    }
}
