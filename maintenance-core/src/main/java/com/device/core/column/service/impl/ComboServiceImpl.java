package com.device.core.column.service.impl;

import com.device.mbg.domain.entity.Role;
import com.device.mbg.service.IRoleService;
import com.device.core.column.entity.dto.ComboDTO;
import com.device.core.column.service.ComboService;
import com.device.core.user.entity.Dict;
import com.device.core.user.entity.enums.DictEnum;
import com.device.core.user.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/3 11:06
 */
@Service
public class ComboServiceImpl implements ComboService {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IDictService dictService;

    @Override
    public ComboDTO combo(Object key, String name, String value, String label) {
        return ComboDTO.builder()
            .key(key)
            .name(name)
            .value(value)
            .label(label)
            .build();
    }

    @Override
    public List<ComboDTO> getRole() {
        List<ComboDTO> comboDTOS = new ArrayList<>();
        List<Role> roles = roleService.queryAllCache();
        roles.forEach(role -> comboDTOS.add(combo(role.getId(), role.getName(), role.getId(), role.getName())));
        return comboDTOS;
    }

    @Override
    public List<ComboDTO> getErrorType() {
        List<ComboDTO> comboDTOS = new ArrayList<>();
        List<Dict> dicts = dictService.queryAll(DictEnum.ERROR_TYPE);
        dicts.forEach(dict -> comboDTOS.add(combo(dict.getId(), dict.getName(), dict.getId(), dict.getName())));
        return comboDTOS;
    }
}