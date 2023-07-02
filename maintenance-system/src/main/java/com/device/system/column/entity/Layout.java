package com.device.system.column.entity;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 显示列表
 * </p>
 *
 * @author ${author}
 * @since 2020-07-28
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_layout")
@Schema(name = "Layout对象", description = "显示列表")
public class Layout extends Model<Layout> {

    private static final long serialVersionUID = 1L;

    public static final String LAYOUT_SESSION_KEY = StpUtil.getLoginId() + "_" + "LAYOUT_SESSION_KEY";

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String tenantId;

    private String name;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
