package com.device.mbg.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.device.common.utils.R;
import com.device.mbg.service.ITenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户表 前端控制器
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-28
 */
@RestController
@RequestMapping("/tenant")
@Tag(name = "租户管理")
public class TenantController {

    @Autowired
    private ITenantService tenantService;

    @Operation(summary = "查询所有租户")
    @GetMapping(value = "/queryAll")
    public R queryAll() {
        return R.ok().data(new LambdaQueryChainWrapper<>(tenantService.getBaseMapper()).list());
    }
}