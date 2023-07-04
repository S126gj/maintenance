package com.device.mbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.device.mbg.domain.criteria.MenuCriteria;
import com.baomidou.mybatisplus.extension.service.IService;
import com.device.mbg.domain.dto.MenuNode;
import com.device.mbg.domain.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-28
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 分页查询后台菜单
     * @param criteria
     * @param page
     * @return
     */
    Map<String, Object> queryAll(MenuCriteria criteria, Page page);

    /**
     * 树状结构返回所有菜单列表
     * @return
     */
    List<MenuNode> treeList();

    /**
     * 树状结构返回所有菜单列表
     * @return
     */
    List<MenuNode> treeListByUserId(String userId);

    /**
     * 树状结构返回所有菜单列表
     * @return
     */
    List<MenuNode> treeListByRoleId(String roleId);

    /**
     * 修改菜单显示状态
     * @param id        菜单id
     * @param hidden    0-显示 1-隐藏
     */
    void updateHidden(String id, Integer hidden);

    /**
     * 创建菜单
     * @param menu
     */
    void create(Menu menu);

    /**
     * 修改菜单
     * @param menu
     */
    void update(Menu menu);

    /**
     * 删除菜单
     * @param id
     */
    void delete(String id);
}
