package com.device.mbg.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 租户表
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
@TableName("tenant")
@Schema(name = "Tenant", description = "租户表")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "开始日期")
    @TableField("begin_date")
    private LocalDateTime beginDate;

    @Schema(description = "到期日期")
    @TableField("end_date")
    private LocalDateTime endDate;

    @Schema(description = "工厂实例id")
    @TableField("instance_id")
    private String instanceId;

    @Schema(description = "授权信息")
    @TableField("license")
    private String license;

    @Schema(description = "创建日期")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @Schema(description = "修改日期")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
