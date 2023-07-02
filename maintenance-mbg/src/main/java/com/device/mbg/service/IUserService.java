package com.device.mbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.device.mbg.domain.criteria.UserCriteria;
import com.device.mbg.domain.dto.UserCreateParam;
import com.device.mbg.domain.dto.UserRegisterParam;
import com.device.mbg.domain.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
public interface IUserService extends IService<User> {

    /**
     * 注册用户
     * @param userRegisterParam
     */
    void register(UserRegisterParam userRegisterParam);

    /**
     * 禁用用户
     * @param id
     */
    void disableUser(String id);

    /**
     * 查询所有用户
     * @param criteria
     * @param page
     * @return
     */
    Map<String, Object> queryAll(UserCriteria criteria, Page page);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 新增用户
     * @param user
     */
    void create(UserCreateParam user);

    /**
     * 修改用户
     * @param user
     */
    void update(UserCreateParam user);

    // /**
    //  * 给用户分配角色
    //  * @param userId
    //  * @param roleIds
    //  */
    // void updateRole(String userId, List<String> roleIds);

    /**
     * 根据租户id与用户名查询对应用户
     * 此方法忽略租户，不会自动拼接 tenant_id
     * @param tenantId  租户id
     * @param username  用户名
     * @return
     */
    User selectByTenantIdAndUserName(String tenantId, String username);

    /**
     * 修改密码
     * @param id        用户id
     * @param password  密码
     */
    void updatePassword(String id, String password);
}
