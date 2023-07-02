package com.device.mbg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.device.mbg.domain.entity.Menu;
import com.device.mbg.domain.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色菜单关系表 Mapper 接口
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Select("select * from sys_menu where id in (select menu_id from sys_role_menu where role_id = #{roleId})")
    List<Menu> getMenuListByRoleId(@Param("roleId") String roleId);
}
