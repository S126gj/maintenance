package com.device.mbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.device.mbg.domain.criteria.RoleCriteria;
import com.device.mbg.domain.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
public interface IRoleService extends IService<Role> {

    /**
     * 查询所有角色
     * @param criteria
     * @param page
     * @return
     */
    Map<String, Object> queryAll(RoleCriteria criteria, Page page);

    /**
     * 根据id获取对应角色信息
     * @param id
     * @param flag  是否查询角色对应菜单
     * @return
     */
    Role findById(String id, boolean flag);

    /**
     * 创建角色
     * @param role
     */
    void create(Role role);

    /**
     * 批量删除角色
     * @param roleIds
     */
    void delete(List<String> roleIds);

    /**
     * 修改角色状态
     * @param id
     * @param status
     */
    void updateStatus(String id, Integer status);
}
