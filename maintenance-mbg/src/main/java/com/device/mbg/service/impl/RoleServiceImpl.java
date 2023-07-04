package com.device.mbg.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.device.common.constanst.CacheKey;
import com.device.common.exception.Checker;
import com.device.common.exception.Errors;
import com.device.common.utils.RedisUtils;
import com.device.mbg.domain.criteria.RoleCriteria;
import com.device.mbg.domain.entity.Menu;
import com.device.mbg.domain.entity.Role;
import com.device.mbg.domain.entity.UserRole;
import com.device.mbg.mapper.RoleMapper;
import com.device.mbg.service.IMenuService;
import com.device.mbg.service.IRoleMenuService;
import com.device.mbg.service.IRoleService;
import com.device.mbg.service.IUserRoleService;
import com.device.mbg.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
@Slf4j
@Service
@CacheConfig(cacheNames = CacheKey.ROLE)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public Map<String, Object> queryAll(RoleCriteria criteria, Page page) {
        IPage<Role> iPage = baseMapper.queryAll(criteria, page);
        return PageUtil.toPage(iPage);
    }

    @Override
    @Cacheable(key = "#root.target.getKey()")
    public List<Role> queryAllCache() {
        return baseMapper.selectList(null);
    }

    @Override
    public Role findById(String id, boolean flag) {
        Role role = new Role();
        Role r = baseMapper.selectById(id);
        BeanUtil.copyProperties(r, role);

        // 查询对应菜单
        Checker.ifThen(flag, then -> role.setMenus(menuService.treeListByRoleId(id)));
        log.info("[RoleServiceImpl][findById] role:{}", JSONUtil.toJsonStr(role));
        return role;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(Role role) {
        checkRoleName(role);
        this.saveOrUpdate(role);
        // 绑定角色菜单
        Checker.ifNotEmptyThen(role.getMenus(),
            then -> roleMenuService.bind(role.getId(), then.stream().map(Menu::getId).collect(Collectors.toList())));
        delCaches();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<String> roleIds) {
        Checker.ifNotEmptyThen(roleIds, then -> {
            then.forEach(roleId -> {
                // 若还有此角色绑定的用户，则不能被删除
                List<UserRole> userRoles = new LambdaQueryChainWrapper<>(userRoleService.getBaseMapper()).eq(
                    UserRole::getRoleId, roleId).list();
                Checker.ifThrow(userRoles.size() > 0, () -> Errors.BIZ.exception("当前角色被其他用户绑定，无法删除!"));
            });
            baseMapper.deleteBatchIds(then);
        });
        delCaches();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStatus(String id, Integer status) {
        Role role = baseMapper.selectById(id);
        role.setStatus(status);
        baseMapper.updateById(role);
    }

    /**
     * 校验角色名称
     * @param role
     */
    public void checkRoleName(Role role) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<Role>().eq(Role::getName, role.getName());
        Checker.ifStringNotEmpty(role.getId(), then -> wrapper.ne(Role::getId, role.getId()));
        Role r = baseMapper.selectOne(wrapper);
        Checker.ifThrow((Objects.nonNull(r) && !role.getName().equals(r.getName())) || Objects.nonNull(r),
            () -> Errors.BIZ.exception("此名称的角色已存在，请勿重复添加！"));
    }

    private void delCaches(){
        redisUtils.del(getKey());
    }
    public String getKey(){
        return  String.format("%s:", StpUtil.getLoginId());
    }
}
