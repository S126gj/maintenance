package com.device.mbg.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.device.mbg.domain.criteria.MenuCriteria;
import com.device.mbg.domain.dto.MenuNode;
import com.device.mbg.domain.entity.Menu;
import com.device.mbg.mapper.MenuMapper;
import com.device.mbg.service.IMenuService;
import com.device.mbg.service.IRoleMenuService;
import com.device.mbg.utils.PageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-28
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public Map<String, Object> queryAll(MenuCriteria criteria, Page page) {
        IPage<Menu> iPage = baseMapper.queryAll(criteria, page);
        return PageUtil.toPage(iPage);
    }

    @Override
    public List<MenuNode> treeList() {
        List<Menu> menuList = new LambdaQueryChainWrapper<>(baseMapper).orderByAsc(Menu::getSort).list();
        List<MenuNode> result = menuList.stream()
            .filter(menu -> menu.getPid().equals("0"))
            .map(menu -> covertMenuNode(menu, menuList))
            .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<MenuNode> treeListByRoleId(String roleId) {
        List<Menu> menuList = roleMenuService.getMenuListByRoleId(roleId);
        List<MenuNode> result = menuList.stream()
            .filter(menu -> menu.getPid().equals("0"))
            .map(menu -> covertMenuNode(menu, menuList))
            .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<MenuNode> treeListByUserId(String userId) {
        List<Menu> menuList = roleMenuService.getMenuListByUserId(userId);
        List<MenuNode> result = menuList.stream()
            .filter(menu -> menu.getPid().equals("0"))
            .map(menu -> covertMenuNode(menu, menuList))
            .collect(Collectors.toList());
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateHidden(String id, Integer hidden) {
        Menu menu = baseMapper.selectById(id);
        menu.setHidden(hidden);
        baseMapper.updateById(menu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(Menu menu) {
        baseMapper.insert(menu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Menu menu) {
        baseMapper.updateById(menu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        baseMapper.deleteById(id);
    }

    /**
     * 递归填充菜单
     * @param menu
     * @param menuList
     * @return
     */
    private MenuNode covertMenuNode(Menu menu, List<Menu> menuList) {
        MenuNode node = new MenuNode();
        BeanUtils.copyProperties(menu, node);
        List<MenuNode> children = menuList.stream()
            .filter(subMenu -> subMenu.getPid().equals(menu.getId()))
            .map(subMenu -> covertMenuNode(subMenu, menuList))
            .collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
