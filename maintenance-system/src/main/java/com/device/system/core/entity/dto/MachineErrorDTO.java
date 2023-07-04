package com.device.system.core.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.device.system.core.entity.MachineError;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/3 15:26
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "设备故障DTO")
public class MachineErrorDTO {

    @Schema(description = "ID")
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
    private LocalDateTime gmtCreate;

    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;
}