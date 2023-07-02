package com.device.mbg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.device.common.utils.R;
import com.device.mbg.domain.criteria.MenuCriteria;
import com.device.mbg.domain.entity.Menu;
import com.device.mbg.service.IMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-28
 */
@RestController
@RequestMapping("/menu")
@Tag(name = "系统菜单管理")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Operation(summary = "分页查询后台菜单")
    @GetMapping(value = "/queryAll")
    public R queryAll(MenuCriteria criteria, Page page) {
        return R.ok().data(menuService.queryAll(criteria, page));
    }

    @Operation(summary = "根据ID获取菜单详情")
    @GetMapping(value = "/queryOne")
    public R queryOne(@Parameter(description = "菜单id") @RequestParam(value = "id") String id) {
        return R.ok().data(menuService.getById(id));
    }

    @Operation(summary = "树状返回所有菜单")
    @GetMapping(value = "/tree")
    public R tree() {
        return R.ok().data(menuService.treeList());
    }

    @Operation(summary = "修改菜单显示状态")
    @PostMapping(value = "/updateHidden")
    public R updateHidden(@Parameter(description = "菜单id") @RequestParam(value = "id") String id,
                          @Parameter(description = "0-显示 1-隐藏") @RequestParam(value = "hidden") Integer hidden) {
        menuService.updateHidden(id, hidden);
        return R.ok();
    }

    @Operation(summary = "添加菜单")
    @PostMapping(value = "/create")
    public R create(@RequestBody Menu menu) {
        menuService.create(menu);
        return R.ok();
    }

    @Operation(summary = "修改菜单")
    @PostMapping(value = "/update")
    public R update(@RequestBody Menu menu) {
        menuService.update(menu);
        return R.ok();
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping(value = "/delete")
    public R delete(@Parameter(description = "菜单id") @RequestParam(value = "id") String id) {
        menuService.delete(id);
        return R.ok();
    }

}