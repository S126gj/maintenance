package com.device.mbg.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.device.common.constanst.Constanst;
import com.device.common.exception.Checker;
import com.device.common.exception.Errors;
import com.device.common.utils.StringUtil;
import com.device.mbg.domain.criteria.UserCriteria;
import com.device.mbg.domain.dto.UserCreateParam;
import com.device.mbg.domain.dto.UserRegisterParam;
import com.device.mbg.domain.entity.Role;
import com.device.mbg.domain.entity.User;
import com.device.mbg.domain.vo.UserVO;
import com.device.mbg.mapper.UserMapper;
import com.device.mbg.service.IRoleMenuService;
import com.device.mbg.service.IUserRoleService;
import com.device.mbg.service.IUserService;
import com.device.mbg.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IUserRoleService userRoleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegisterParam userRegisterParam) {
        // 校验用户名是否重复
        checkUsername(userRegisterParam.getUsername());
        User user = User.builder()
            .username(userRegisterParam.getUsername())
            .tenantId(userRegisterParam.getTenantId())
            .password(SecureUtil.md5(userRegisterParam.getPassword()))
            .phone(userRegisterParam.getPhone())
            .gender(userRegisterParam.getGender())
            .build();
        baseMapper.insert(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStatus(String id, Integer status) {
        User user = baseMapper.selectById(id);
        user.setStatus(status);
        baseMapper.updateById(user);
    }

    @Override
    public Map<String, Object> queryAll(UserCriteria criteria, Page page) {
        IPage<UserVO> iPage = baseMapper.queryAll(criteria, page);
        return PageUtil.toPage(iPage);
    }

    @Override
    public User findById(String id) {
        User user = baseMapper.findById(id);
        user.setRoleList(roleMenuService.getRoleListByUserId(user.getId()).stream().map(Role::getId).toList());
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(UserCreateParam user) {
        // 校验用户名是否重复
        checkUsername(user.getUsername());
        // 若密码为空则给默认密码
        String password = StringUtil.isNotBlank(user.getPassword()) ? SecureUtil.md5(user.getPassword()) : SecureUtil.md5(
            Constanst.DEFAULT_PASSWORD);
        user.setPassword(password);
        baseMapper.insert(user);
        // 角色绑定
        userRoleService.bindRole(user.getId(), user.getRoleList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UserCreateParam user) {
        baseMapper.updateById(user);
        // 角色绑定
        userRoleService.bindRole(user.getId(), user.getRoleList());
    }

    // @Transactional(rollbackFor = Exception.class)
    // @Override
    // public void updateRole(String userId, List<String> roleIds) {
    //     // 先删除用户原有绑定角色
    //     userRoleService.deleteByUserId(userId);
    //     // 绑定新角色
    //     if (CollUtil.isNotEmpty(roleIds)) {
    //         List<UserRole> userRoleList = new ArrayList<>();
    //         roleIds.forEach(roleId -> userRoleList.add(UserRole.builder().userId(userId).roleId(roleId).build()));
    //         userRoleService.saveBatch(userRoleList);
    //     }
    //
    // }

    @Override
    public User selectByTenantIdAndUserName(String tenantId, String username) {
        return baseMapper.selectByTenantIdAndUserName(tenantId, username);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePassword(String id, String password) {
        // 若未传入密码，则设置默认密码
        password = Optional.ofNullable(password).orElse(Constanst.DEFAULT_PASSWORD);
        User user = baseMapper.selectById(id);
        Checker.ifNullThrow(user, () -> Errors.BIZ.exception("该用户不存在!"));
        user.setPassword(SecureUtil.md5(password));
        baseMapper.updateById(user);
    }

    private void checkUsername(String username) {
        User user = new LambdaQueryChainWrapper<>(baseMapper).eq(User::getUsername, username).one();
        Checker.ifNotEmptyThrow(user, () -> Errors.BIZ.exception("用户名重复!"));
    }
}
