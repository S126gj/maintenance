package com.device.mbg.auth.handler;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.json.JSONUtil;
import com.device.common.utils.StringUtil;
import com.device.mbg.domain.entity.Menu;
import com.device.mbg.domain.entity.Role;
import com.device.mbg.service.IRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限验证接口拓展
 *
 * @Author: Guoji Shen
 * @Date: 2023/6/26 13:35
 */
@Slf4j
@Component
public class StpInterfaceHandler implements StpInterface {

    // 用户登录 loginType --- 此值取决于 StpUtil 中的 Type 字段
    private static final String USER_LOGIN_TYPE = "login";
    // 客户登录 loginType --- 此值取决于 StpCustomerUtil 中的 Type 字段
    private static final String CUSTOMER_LOGIN_TYPE = "customer";

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> permissionList = new ArrayList<>();
        if (USER_LOGIN_TYPE.equals(loginType)) {
            List<Menu> menuList = roleMenuService.getMenuListByUserId(loginId.toString());
            // 过滤出不为空的权限
            permissionList = menuList.stream().map(Menu::getPermission).filter(StringUtil::isNotBlank).collect(Collectors.toList());
            log.info("[StpInterfaceImpl][getPermissionList] 用户 permissionList:{}", JSONUtil.toJsonStr(permissionList));
        } else if (CUSTOMER_LOGIN_TYPE.equals(loginType)) {

            log.info("[StpInterfaceImpl][getPermissionList] 客户 permissionList:{}", JSONUtil.toJsonStr(permissionList));
        }
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> roleNameList = new ArrayList<>();
        if (USER_LOGIN_TYPE.equals(loginType)) {
            List<Role> roleList = roleMenuService.getRoleListByUserId(loginId.toString());
            roleNameList = roleList.stream().map(Role::getName).collect(Collectors.toList());
            log.info("[StpInterfaceImpl][getRoleList] 用户 roleNameList:{}", JSONUtil.toJsonStr(roleNameList));
        } else if (CUSTOMER_LOGIN_TYPE.equals(loginType)) {

            log.info("[StpInterfaceImpl][getRoleList] 客户 roleNameList:{}", JSONUtil.toJsonStr(roleNameList));
        }
        return roleNameList;
    }
}
