package com.device.system.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.device.common.utils.R;
import com.device.system.core.entity.criteria.MachineCriteria;
import com.device.system.core.entity.dto.MachineDTO;
import com.device.system.core.service.IMachineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 设备型号表 前端控制器
 * </p>
 *
 * @author GuojiShen
 * @since 2023-07-03
 */
@RestController
@RequestMapping("/machine")
@Tag(name = "设备型号")
public class MachineController {

    @Autowired
    private IMachineService machineService;

    @Operation(summary = "新增设备型号")
    @PostMapping(value = "/create")
    public R create(@Valid @RequestBody MachineDTO machineDTO) {
        machineService.create(machineDTO);
        return R.ok();
    }

    @Operation(summary = "查询所有设备型号")
    @GetMapping(value = "/queryAll")
    public R queryAll(MachineCriteria criteria, Page page) {
        return R.ok().data(machineService.queryAll(criteria, page));
    }

    @Operation(summary = "查询单条设备型号")
    @GetMapping(value = "/queryOne")
    public R queryOne(@Parameter(description = "设备id") @RequestParam(value = "id") String id) {
        return R.ok().data(machineService.queryOne(id));
    }
}