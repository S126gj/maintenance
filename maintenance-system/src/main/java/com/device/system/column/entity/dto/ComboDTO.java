package com.device.system.column.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/3 11:07
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComboDTO {

    @Schema(description = "取id")
    private Object key;

    @Schema(description = "标签显示名")
    private String label;

    @Schema(description = "前台给后台的值, 一般取id")
    private String value;

    @Schema(description = "")
    private String name;

    @Schema(description = "是否启用")
    private boolean enableLocation;

}
