package com.device.mbg.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.device.mbg.domain.criteria.RoleCriteria;
import com.device.mbg.domain.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询对应角色
     * @param userId    用户id
     * @return
     */
    List<Role> getRoleList(@Param("userId") String userId);

    /**
     * 查询所有角色
     * @param criteria
     * @param page
     * @return
     */
    IPage<Role> queryAll(@Param("criteria") RoleCriteria criteria, Page page);
}
