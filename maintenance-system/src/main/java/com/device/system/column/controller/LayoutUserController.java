package com.device.system.column.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.device.common.utils.R;
import com.device.system.column.entity.LayoutUser;
import com.device.system.column.service.LayoutUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 * 用户布局表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-06-09
 */

@RestController
@RequestMapping("/acl/layout-user")
@Tag(name = "用户布局")
public class LayoutUserController {

    @Autowired
    private LayoutUserService layoutUserService;

    @Operation(summary = "新增修改用户布局")
    @PostMapping("/saveOrUpdate")
    public R query(@RequestBody LayoutUser layoutUser) {
        LayoutUser user = new LambdaQueryChainWrapper<>(layoutUserService.getBaseMapper()).eq(LayoutUser::getUserId,
            layoutUser.getUserId()).one();
        if (Objects.isNull(user)) {
            layoutUserService.save(layoutUser);
        } else {
            user.setContent(layoutUser.getContent());
            layoutUserService.updateById(user);
        }
        return R.ok();
    }

    @Operation(summary = "查询用户布局")
    @GetMapping("/getByUserId")
    public R query(@Parameter(description = "用户id") @RequestParam(value = "userId") String userId) {
        LayoutUser layoutUser = new LambdaQueryChainWrapper<>(layoutUserService.getBaseMapper())
            .eq(LayoutUser::getUserId, userId).one();
        return R.ok().data(layoutUser);
    }

}

