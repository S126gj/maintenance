package com.device.system.core.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.device.file.entity.File;
import com.device.system.core.entity.MachineError;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/3 16:46
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "设备型号VO")
public class MachineVO {

    @Schema(description = "ID")
    private String id;

    @NotBlank(message = "设备名称不能为空")
    @Schema(description = "设备名称")
    @TableField("name")
    private String name;

    @NotBlank(message = "设备型号不能为空")
    @Schema(description = "设备型号")
    @TableField("type")
    private String type;

    @Schema(description = "设备重量(kg)")
    @TableField("weight")
    private Integer weight;

    @Schema(description = "最大工作长度(mm)")
    @TableField("max_length")
    private Integer maxLength;

    @Schema(description = "最大工作宽度(mm)")
    @TableField("max_width")
    private Integer maxWidth;

    @Schema(description = "最小工作长度(mm)")
    @TableField("min_length")
    private Integer minLength;

    @Schema(description = "最小工作宽度(mm)")
    @TableField("min_width")
    private Integer minWidth;

    @Schema(description = "额定电压(V)")
    @TableField("rated_voltage")
    private Integer ratedVoltage;

    @Schema(description = "外型尺寸(mm)")
    @TableField("size")
    private Integer size;

    @Schema(description = "工作速率")
    @TableField("work_rate")
    private String workRate;

    @Schema(description = "整机功率(KW)")
    @TableField("power")
    private Double power;

    @Schema(description = "设备图片")
    @TableField("img_path")
    private String imgPath;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "附件id")
    private List<File> files;

    @Schema(description = "故障代码")
    private List<MachineError> machineErrors;

}
