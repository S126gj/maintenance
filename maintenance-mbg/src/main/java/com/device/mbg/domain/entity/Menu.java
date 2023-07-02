package com.device.mbg.domain.entity;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.device.common.utils.StringUtil;
import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-28
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_menu")
@Schema(name = "Menu", description = "系统菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "父级ID")
    @TableField("pid")
    private String pid;

    @Schema(description = "租户id")
    @TableField("tenant_id")
    private String tenantId;

    @Schema(description = "菜单名称")
    @TableField("title")
    private String title;

    @Schema(description = "菜单排序")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "前端名称")
    @TableField("name")
    private String name;

    @Schema(description = "前端路径")
    @TableField("path")
    private String path;

    @Schema(description = "前端图标")
    @TableField("icon")
    private String icon;

    @Schema(description = "权限标识")
    @TableField("permission")
    private String permission;

    @Schema(description = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
    @TableField("type")
    private Integer type;

    @Schema(description = "前端资源路径")
    @TableField("component")
    private String component;

    @Schema(description = "是否隐藏 0-否 1-是")
    @TableField("hidden")
    private Integer hidden;

    @Schema(description = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @Schema(description = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @Schema(description = "前端所需拼接")
    @TableField(exist = false)
    private Map<String, String> meta;

    public Map<String, String> getMeta() {
        return new ImmutableMap.Builder<String, String>()
            .put("title", StringUtil.isNotBlank(title) ? title : null)
            .put("icon", StringUtil.isNotBlank(icon) ? icon : null)
            .build();
    }
}
