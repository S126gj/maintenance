package com.device.system.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备型号表
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
@TableName("sys_machine")
@Schema(name = "Machine", description = "设备型号")
public class Machine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "租户id")
    @TableField("tenant_id")
    private String tenantId;

    @NotBlank(message = "设备名称不能为空")
    @Schema(description = "设备名称")
    @TableField("name")
    private String name;

    @NotBlank(message = "设备型号不能为空")
    @Schema(description = "设备型号")
    @TableField("type")
    private String type;

    @Schema(description = "设备重量")
    @TableField("weight")
    private Integer weight;

    @Schema(description = "最大工作长度")
    @TableField("max_length")
    private Integer maxLength;

    @Schema(description = "最大工作宽度")
    @TableField("max_width")
    private Integer maxWidth;

    @Schema(description = "最小工作长度")
    @TableField("min_length")
    private Integer minLength;

    @Schema(description = "最小工作宽度")
    @TableField("min_width")
    private Integer minWidth;

    @Schema(description = "额定电压")
    @TableField("rated_voltage")
    private Integer ratedVoltage;

    @Schema(description = "外型尺寸")
    @TableField("size")
    private Integer size;

    @Schema(description = "工作速率")
    @TableField("work_rate")
    private String workRate;

    @Schema(description = "整机功率")
    @TableField("power")
    private Double power;

    @Schema(description = "设备图片")
    @TableField("img_path")
    private String imgPath;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @Schema(description = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
