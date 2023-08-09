package com.device.system.api.controller;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.sign.SaSignUtil;
import com.device.common.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * satoken签名对接，具体可查看
 * https://sa-token.cc/doc.html#/plugin/api-sign
 * 需要注意的是，/api下的所有接口均需要放开权限校验
 * @Author: Guoji Shen
 * @Date: 2023/8/9 15:10
 */
@RestController
@RequestMapping("/api")
@Tag(name = "对接开放接口")
@Slf4j
public class ApiController {

    @Operation(summary = "测试")
    @GetMapping("/test")
    public R test(@Parameter(description = "用户id") @RequestParam(value = "userId") String userId,
        @Parameter(description = "测试") @RequestParam(value = "test") String test) {
        SaSignUtil.checkRequest(SaHolder.getRequest());
        log.info("[ApiController][test] userId[{}], test[{}]", userId, test);
        return R.ok().data("testSuccess    1");
    }

    @Operation(summary = "测试2")
    @GetMapping("/test2")
    public R test2() {
        SaSignUtil.checkRequest(SaHolder.getRequest());
        return R.ok().data("testSuccess    2");
    }
}