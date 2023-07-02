package com.device.mbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.device.mbg.domain.entity.Tenant;

/**
 * <p>
 * 租户表 服务类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-28
 */
public interface ITenantService extends IService<Tenant> {

    Tenant getTenantByIdOrName(String tenant);
}
