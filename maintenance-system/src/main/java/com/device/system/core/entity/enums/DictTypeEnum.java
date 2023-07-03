package com.device.system.core.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/3 08:55
 */
@AllArgsConstructor
public enum DictTypeEnum {
    BASE_DICT(0, "基础字典"),
    ;

    @Getter
    private Integer code;
    @Getter
    private String msg;
}