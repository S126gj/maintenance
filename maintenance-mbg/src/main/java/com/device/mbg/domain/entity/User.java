package com.device.mbg.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.device.common.annotation.Phone;
import com.device.mbg.handler.ListConvertHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_user")
@Schema(name = "User", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "租户id")
    @TableField("tenant_id")
    private String tenantId;

    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Phone
    @Schema(description = "手机号码")
    @TableField("phone")
    private String phone;

    @Schema(description = "真实姓名")
    @TableField("real_name")
    private String realName;

    @Schema(description = "身份证号")
    @TableField("id_card")
    private String idCard;

    @Email(message = "邮箱格式不正确")
    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "头像")
    @TableField("icon")
    private String icon;

    @Schema(description = "帐号启用状态:0->禁用；1->启用")
    @TableField("status")
    private Integer status;

    @Schema(description = "性别：0->未知；1->男；2->女")
    @TableField("gender")
    private Integer gender;

    @Schema(description = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @Schema(description = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @Schema(description = "角色")
    @TableField(typeHandler = ListConvertHandler.class, exist = false)
    private List<String> roleList;
}
