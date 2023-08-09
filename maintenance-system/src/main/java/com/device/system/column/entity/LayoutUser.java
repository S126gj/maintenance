package com.device.system.column.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户布局表
 * </p>
 *
 * @author ${author}
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_layout_user")
@Schema(name="LayoutUser对象", description="用户布局表")
public class LayoutUser extends Model<LayoutUser> {

    private static final long serialVersionUID=1L;

    @Schema(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(name = "用户id")
    private String userId;

    @Schema(name = "内容")
    private String content;

    @Schema(name = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @Schema(name = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}