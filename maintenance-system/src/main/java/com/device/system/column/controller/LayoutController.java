package com.device.system.column.controller;

import com.device.common.utils.R;
import com.device.system.column.service.LayoutService;
import com.device.system.column.entity.dto.LayoutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/acl/layout")
@RequiredArgsConstructor
@Tag(name = "布局")
public class LayoutController {

    private final LayoutService layoutService;

    @Operation(summary = "查询显示列表")
    @GetMapping
    public R query() {
        return R.ok().data(layoutService.select());
    }

    @Operation(summary = "修改显示列表")
    @PutMapping
    public R update(@RequestBody LayoutDTO resources) {
        layoutService.update(resources);
        return R.ok();
    }

    @Operation(summary = "查询显示列表")
    @GetMapping("/get")
    public R get(String id) {
        return R.ok().data(layoutService.findById(id));
    }


}

