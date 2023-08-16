package com.device.core.column.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.device.core.column.entity.LayoutDetails;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-07-28
 */
public interface LayoutDetailsMapper extends BaseMapper<LayoutDetails> {

    /**
     * 根据layoutId查找自己的显示列
     */
    @Select("select * from sys_layout_d where layout_id=#{layoutId} order by sort")
    List<LayoutDetails> findByLayoutId(@Param("layoutId") String layoutId);


    /**
     * @param id      明细ID
     * @param label   显示名称
     * @param width   宽度
     * @param visible 显隐
     * @param sort    排序
     */
    @Update("update sys_layout_d set width=#{width},label=#{label},visible=#{visible},sort=#{sort},auth_config=#{authConfig},validate_rules=#{validateRules},sortable=#{sortable}  where id=#{id}")
    void update(@Param("id") String id,
        @Param("label") String label,
        @Param("width") Integer width,
        @Param("visible") Boolean visible,
        @Param("sort") Integer sort,
        @Param("authConfig") String authConfig,
        @Param("validateRules") String validateRules,
        @Param("sortable") Boolean sortable
    );


    /**
     * 新增
     */
    @Insert(
        "insert into sys_layout_d (id,tenant_id,layout_id,sort,field_name,label,width,input_type,visible,readonly,layout_type,data_source,validate_rules,filterable,auth_config,sortable )"
            +
            "values(REPLACE(RAND(),'0.',''),#{layoutId},#{tenantId},#{sort},#{fieldName},#{label},#{width},#{inputType},#{visible},#{readonly},#{layoutType},#{dataSource},#{validateRules},#{filterable},#{authConfig},#{sortable})")
    void insert(
        @Param("layoutId") String layoutId,
        @Param("tenantId") String tenantId,
        @Param("sort") Integer sort,
        @Param("fieldName") String fieldName,
        @Param("label") String label,
        @Param("width") Integer width,
        @Param("inputType") String inputType,
        @Param("visible") Boolean visible,
        @Param("readonly") Boolean readonly,
        @Param("layoutType") String layoutType,
        @Param("dataSource") String dataSource,
        @Param("validateRules") String validateRules,
        @Param("filterable") Boolean filterable,
        @Param("authConfig") String authConfig,
        @Param("sortable") Boolean sortable
    );
}
