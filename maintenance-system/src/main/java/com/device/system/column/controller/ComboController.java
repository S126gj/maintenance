package com.device.system.column.controller;

import com.device.common.utils.R;
import com.device.system.column.service.ComboService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/3 11:05
 */
@RestController
@RequestMapping("/combo/layout")
@RequiredArgsConstructor
@Tag(name = "布局")
public class ComboController {

    @Autowired
    private ComboService comboService;

    @Operation(summary = "获取角色")
    @GetMapping("/getRole")
    public R getRole() {
        return R.ok().data(comboService.getRole());
    }

    @Operation(summary = "故障类型")
    @GetMapping("/getErrorType")
    public R getErrorType() {
        return R.ok().data(comboService.getErrorType());
    }

}