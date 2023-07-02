package com.device.mbg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.device.common.utils.R;
import com.device.mbg.domain.criteria.RoleCriteria;
import com.device.mbg.domain.entity.Role;
import com.device.mbg.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
@RestController
@RequestMapping("/role")
@Tag(name = "角色管理")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Operation(summary = "获取所有角色")
    @GetMapping(value = "/queryAll")
    public R queryAll(RoleCriteria criteria, Page page) {
        return R.ok().data(roleService.queryAll(criteria, page));
    }

    @Operation(summary = "根据id获取单条角色")
    @GetMapping(value = "/queryOne")
    public R queryOne(@Parameter(description = "角色id") @RequestParam(value = "id") String id) {
        return R.ok().data(roleService.findById(id, true));
    }

    @Operation(summary = "创建角色")
    @PostMapping(value = "/create")
    public R create(@Validated @RequestBody Role role) {
        roleService.create(role);
        return R.ok();
    }

    @Operation(summary = "批量删除角色")
    @PostMapping(value = "/delete")
    public R delete(@Parameter(description = "角色id") @RequestParam(value = "roleIds") List<String> roleIds) {
        roleService.delete(roleIds);
        return R.ok();
    }

    @Operation(summary = "修改角色状态")
    @PostMapping(value = "/updateStatus")
    public R updateStatus(@Parameter(description = "角色id") @RequestParam(value = "id") String id,
                          @Parameter(description = "0->禁用；1->启用") @RequestParam(value = "status") Integer status) {
        roleService.updateStatus(id, status);
        return R.ok();
    }

}