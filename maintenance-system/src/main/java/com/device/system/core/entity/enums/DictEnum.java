package com.device.system.core.entity.enums;

import com.device.common.exception.Checker;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/30 16:16
 */
@AllArgsConstructor
public enum DictEnum {
    FAULT_TYPE(DictTypeEnum.BASE_DICT, "故障类型"),
    ;

    @Getter
    private DictTypeEnum type;
    @Getter
    private String msg;

    public static List<DictEnum> getByType(DictTypeEnum type) {
        List<DictEnum> dictEnums = new ArrayList<>();
        for (DictEnum dict : DictEnum.values()) {
            Checker.ifThen(type.equals(dict.type), then -> dictEnums.add(dict));
        }
        return dictEnums;
    }
}