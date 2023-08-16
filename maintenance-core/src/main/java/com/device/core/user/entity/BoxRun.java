package com.device.core.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备实时运行表
 * </p>
 *
 * @author GuojiShen
 * @since 2023-08-02
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "BoxRun", description = "设备运行")
public class BoxRun implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "查询时间")
    @TableField("last_query_time")
    private LocalDateTime lastQueryTime;

    @Schema(description = "盒子序列号")
    @TableField("box_code")
    private String boxCode;

    @Schema(description = "开始生产数量")
    @TableField("product_number_begin")
    private BigDecimal productNumberBegin;

    @Schema(description = "生产数量")
    @TableField("product_number")
    private BigDecimal productNumber;

    @Schema(description = "平均速率")
    @TableField("avg_speed")
    private BigDecimal avgSpeed;

    @Schema(description = "开始时间")
    @TableField("begin_time")
    private LocalDateTime beginTime;

    @Schema(description = "结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @Schema(description = "时长(分钟)")
    @TableField("duration")
    private Long duration;

    @Schema(description = "设备状态（关机,待机,运行）")
    @TableField("machine_state")
    private String machineState;

    @Schema(description = "创建日期")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @Schema(description = "修改日期")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
