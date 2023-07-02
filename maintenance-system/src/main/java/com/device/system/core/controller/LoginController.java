package com.device.system.core.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import com.device.common.constanst.CacheKey;
import com.device.common.exception.Checker;
import com.device.common.exception.Errors;
import com.device.common.response.CommonCode;
import com.device.common.utils.R;
import com.device.common.utils.RedisUtils;
import com.device.mbg.domain.dto.UserLoginParam;
import com.device.mbg.domain.dto.UserRegisterParam;
import com.device.mbg.domain.entity.Tenant;
import com.device.mbg.domain.entity.User;
import com.device.mbg.domain.vo.LoginVO;
import com.device.mbg.domain.vo.UserInfo;
import com.device.mbg.service.ITenantService;
import com.device.mbg.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: Guoji Shen
 * @Date: 2023/6/27 16:33
 */
@RestController
@RequestMapping("/sso")
@Tag(name = "登录")
public class LoginController {

    @Autowired
    private ITenantService tenantService;
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtils redisUtils;

    @Operation(summary = "注册")
    @PostMapping(value = "/register")
    public R register(@Validated @RequestBody UserRegisterParam userRegisterParam) {
        userService.register(userRegisterParam);
        return R.ok();
    }

    @Operation(summary = "登录")
    @PostMapping(value = "/login")
    public R login(@Validated @RequestBody UserLoginParam userLoginParam) {
        Tenant tenant = tenantService.getTenantByIdOrName(userLoginParam.getTenant());
        // 校验租户
        Checker.ifNullThrow(tenant, () -> Errors.BIZ.exception(CommonCode.TENANT_VALID_ERROR));
        // 用户名不允许重复，可直接根据用户名查询(此接口略过租户自动拼接)
        User user = userService.selectByTenantIdAndUserName(tenant.getId(), userLoginParam.getUsername());
        // 校验用户
        Checker.ifNullThrow(user, () -> Errors.BIZ.exception("用户不存在"));
        // 校验密码
        Checker.ifNotThrow(SecureUtil.md5(userLoginParam.getPassword()).equals(user.getPassword()),
            () -> Errors.BIZ.exception(CommonCode.PASSWORD_VALID_ERROR));
        // 登录
        StpUtil.login(user.getId());
        // 将用户信息放入缓存
        UserInfo userInfo = UserInfo.builder()
            .id(user.getId())
            .username(user.getUsername())
            .icon(user.getIcon())
            .tenantId(tenant.getId())
            .permission(StpUtil.getPermissionList())
            .roleList(StpUtil.getRoleList()).build();
        redisUtils.set(getLoginCacheKey(), userInfo);
        // 返回token与用户信息
        return R.ok().data(LoginVO.builder()
            .token(StpUtil.getTokenValue())
            .userInfo(userInfo)
            .build());
    }

    @Operation(summary = "退出登录")
    @PostMapping(value = "/logout")
    public R logout() {
        try {
            // 删除租户
            redisUtils.del(getLoginCacheKey());
        } finally {
            // 登出
            StpUtil.logout(StpUtil.getLoginId());
        }
        return R.ok();
    }

    /**
     * 登录缓存key
     * @return
     */
    private String getLoginCacheKey() {
        return String.format("%s%s", CacheKey.TENANT, StpUtil.getLoginId());
    }
}