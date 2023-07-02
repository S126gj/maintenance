package com.device.mbg.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.device.mbg.domain.entity.Role;
import com.device.mbg.domain.entity.UserRole;
import com.device.mbg.mapper.UserRoleMapper;
import com.device.mbg.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByUserId(String userId) {
        baseMapper.delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bindRole(String userId, List<Role> roleList) {
        this.deleteByUserId(userId);
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roleIds = roleList.stream().map(Role::getId).collect(Collectors.toList());

            List<UserRole> userRoleList = new ArrayList<>();
            roleIds.forEach(roleId -> userRoleList.add(UserRole.builder().userId(userId).roleId(roleId).build()));
            this.saveBatch(userRoleList);
        }
    }
}
