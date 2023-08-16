package com.device.core.column.service;

import com.device.core.column.entity.dto.ComboDTO;

import java.util.List;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/3 11:06
 */
public interface ComboService {

    ComboDTO combo(Object key, String name, String value, String label);

    /**
     * 获取所有角色
     * @return
     */
    List<ComboDTO> getRole();

    /**
     * 获取故障类型
     * @return
     */
    List<ComboDTO> getErrorType();
}