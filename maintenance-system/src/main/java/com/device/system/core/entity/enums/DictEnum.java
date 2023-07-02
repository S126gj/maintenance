package com.device.system.core.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/30 16:16
 */
@AllArgsConstructor
public enum DictEnum {
    FAULT_TYPE(0, "故障类型"),
    ;

    @Getter
    private Integer code;
    @Getter
    private String msg;
}