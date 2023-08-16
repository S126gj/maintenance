package com.device.core.customer.controller;

import com.device.common.constanst.CacheKey;
import com.device.common.utils.R;
import com.device.common.utils.RedisUtils;
import com.device.mbg.auth.util.StpCustomerUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: Guoji Shen
 * @Date: 2023/7/19 13:06
 */
@RestController
@RequestMapping("/customer/sso")
@Tag(name = "客户手机端:登陆")
public class CustomerLoginController {

    // @Autowired
    // private ICustomerService customerService;
    @Autowired
    private RedisUtils redisUtils;

    @Operation(summary = "登录")
    @PostMapping(value = "/login")
    public R login(String tenantId, String customerName) {
        // Customer customer = customerService.selectByTenantIdAndCustomerName(tenantId, customerName);
        // StpCustomerUtil.login(customer.getId());
        // redisUtils.set(getLoginCacheKey(), tenantId);
        // // 返回token
        // return R.ok().data(CustomerLoginVO.builder()
        //     .token(StpCustomerUtil.getTokenValue())
        //     .build());
        return R.ok();
    }

    private String getLoginCacheKey() {
        return String.format("%s%s", CacheKey.CUSTOMER_TENANT, StpCustomerUtil.getLoginId());
    }
}