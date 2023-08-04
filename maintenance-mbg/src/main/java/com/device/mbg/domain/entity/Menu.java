package com.device.mbg.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.device.common.utils.StringUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    @Schema(description = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
    @TableField(exist = false)
    private String typeDesc;

    @Schema(description = "前端资源路径")
    @TableField("component")
    private String component;

    @Schema(description = "是否隐藏 0-否 1-是")
    @TableField("hidden")
    private Integer hidden;

    @Schema(description = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime gmtCreate;

    @Schema(description = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime gmtModified;

    @Schema(description = "前端所需拼接")
    @TableField(exist = false)
    private Map<String, String> meta;

    public Map<String, String> getMeta() {
        Map<String, String> map = new HashMap<>();
        map.put("title", StringUtil.isNotBlank(title) ? title : null);
        map.put("icon", StringUtil.isNotBlank(icon) ? icon : null);
        return map;
    }

    public String getTypeDesc() {
        String desc = null;
        switch(getType()) {
            case 0 -> desc = "目录";
            case 1 -> desc = "菜单";
            case 2 -> desc = "按钮";
        }
        return desc;
    }
}
