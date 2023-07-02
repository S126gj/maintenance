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

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<Menu> menuList = roleMenuService.getMenuListByUserId(loginId.toString());
        // 过滤出不为空的权限
        List<String> permissionList = menuList.stream().map(Menu::getPermission).filter(StringUtil::isNotBlank).collect(Collectors.toList());
        log.info("[StpInterfaceImpl][getPermissionList] permissionList:{}", JSONUtil.toJsonStr(permissionList));
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<Role> roleList = roleMenuService.getRoleListByUserId(loginId.toString());
        List<String> roleNameList = roleList.stream().map(Role::getName).collect(Collectors.toList());
        log.info("[StpInterfaceImpl][getRoleList] roleNameList:{}", JSONUtil.toJsonStr(roleNameList));
        return roleNameList;
    }
}
