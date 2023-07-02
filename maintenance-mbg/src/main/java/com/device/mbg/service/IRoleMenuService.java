package com.device.mbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.device.mbg.domain.entity.Menu;
import com.device.mbg.domain.entity.Role;
import com.device.mbg.domain.entity.RoleMenu;

import java.util.List;

/**
 * <p>
 * 角色菜单关系表 服务类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    List<Menu> getMenuListByUserId(String userId);

    List<Role> getRoleListByUserId(String userId);

    List<Menu> getMenuListByRoleId(String roleId);

    /**
     * 绑定角色与菜单
     * @param roleId
     * @param menuIds
     */
    void bind(String roleId, List<String> menuIds);
}
