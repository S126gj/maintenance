package com.device.file.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件上传类型
 * @Author: Guoji Shen
 * @Date: 2023/7/3 14:18
 */
@AllArgsConstructor
public enum FileResouceTypeEnum {
    USER("用户模块"),
    MACHINE("设备型号模块"),
    ;


    @Getter
    private String msg;
}