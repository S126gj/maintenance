package com.device.core.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备故障表
 * </p>
 *
 * @author GuojiShen
 * @since 2023-07-03
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_machine_error")
@Schema(name = "MachineError", description = "$!{table.comment}")
public class MachineError implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "租户id")
    @TableField("tenant_id")
    private String tenantId;

    @Schema(description = "设备id")
    @TableField("machine_id")
    private String machineId;

    @Schema(description = "故障代码")
    @TableField("error_code")
    private String errorCode;

    @Schema(description = "故障类型(字典)")
    @TableField("error_type")
    private String errorType;

    @Schema(description = "故障信息")
    @TableField("error_info")
    private String errorInfo;

    @Schema(description = "故障原因")
    @TableField("error_reason")
    private String errorReason;

    @Schema(description = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @Schema(description = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
