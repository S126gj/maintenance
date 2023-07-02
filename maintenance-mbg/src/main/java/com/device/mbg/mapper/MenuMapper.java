package com.device.mbg.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.device.mbg.domain.criteria.MenuCriteria;
import com.device.mbg.domain.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-28
 */
public interface MenuMapper extends BaseMapper<Menu> {

    IPage<Menu> queryAll(@Param("criteria") MenuCriteria criteria, Page page);

    /**
     * 根据用户id查询对应菜单
     * @param userId    用户id
     * @return
     */
    List<Menu> getMenuList(@Param("userId") String userId);
}
