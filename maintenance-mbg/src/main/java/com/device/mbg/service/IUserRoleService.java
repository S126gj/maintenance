package com.device.mbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.device.mbg.domain.entity.Role;
import com.device.mbg.domain.entity.UserRole;

import java.util.List;

/**
 * <p>
 * 用户角色关系表 服务类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 删除当前用户绑定角色
     * @param userId
     */
    void deleteByUserId(String userId);

    /**
     * 绑定角色
     * @param userId
     * @param roleList
     */
    void bindRole(String userId, List<Role> roleList);
}
