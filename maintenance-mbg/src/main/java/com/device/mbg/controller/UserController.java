package com.device.mbg.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.device.common.constanst.CacheKey;
import com.device.common.utils.R;
import com.device.common.utils.RedisUtils;
import com.device.mbg.domain.criteria.UserCriteria;
import com.device.mbg.domain.dto.UserCreateParam;
import com.device.mbg.domain.vo.UserInfo;
import com.device.mbg.service.IMenuService;
import com.device.mbg.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private RedisUtils redisUtils;

    @Operation(summary = "查询所有用户")
    @GetMapping(value = "/queryAll")
    public R queryAll(UserCriteria criteria, Page page) {
        return R.ok().data(userService.queryAll(criteria, page));
    }

    @Operation(summary = "查询单条用户")
    @GetMapping(value = "/queryOne")
    public R queryOne(@Parameter(description = "用户id") @RequestParam(value = "id") String id) {
        return R.ok().data(userService.findById(id));
    }

    @Operation(summary = "获取用户信息")
    @GetMapping(value = "/getUserInfo")
    public R getUserInfo() {
        UserInfo userInfo = (UserInfo) redisUtils.get(String.format("%s%s", CacheKey.TENANT, StpUtil.getLoginId()));
        userInfo.setMenuList(menuService.treeListByUserId(StpUtil.getLoginId().toString()));
        return R.ok().data(userInfo);
    }

    @Operation(summary = "新增用户")
    @PostMapping(value = "/create")
    public R create(@Validated @RequestBody UserCreateParam user) {
        userService.create(user);
        return R.ok();
    }

    @Operation(summary = "修改用户")
    @PostMapping(value = "/update")
    public R update(@RequestBody UserCreateParam user) {
        userService.update(user);
        return R.ok();
    }

    @Operation(summary = "禁用用户")
    @PostMapping(value = "/updateStatus")
    public R updateStatus(@Parameter(description = "用户id") @RequestParam(value = "id") String id,
        @Parameter(description = "0->禁用；1->启用") @RequestParam(value = "status") Integer status) {
        userService.updateStatus(id, status);
        return R.ok();
    }

    @Operation(summary = "修改密码")
    @PostMapping(value = "/updatePassword")
    public R updatePassword(@Parameter(description = "用户id") @RequestParam(value = "id") String id,
            @Parameter(description = "用户密码") @RequestParam(value = "password", required = false) String password) {
        userService.updatePassword(id, password);
        return R.ok();
    }

    // @Operation(summary = "给用户分配角色")
    // @PostMapping(value = "/updateRole")
    // public R updateRole(@Parameter(description = "用户id") @RequestParam(value = "userId") String userId,
    //                     @Parameter(description = "角色id") @RequestParam(value = "roleIds") List<String> roleIds) {
    //     userService.updateRole(userId, roleIds);
    //     return R.ok();
    // }
}
