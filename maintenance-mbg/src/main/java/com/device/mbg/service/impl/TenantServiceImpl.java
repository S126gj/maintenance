package com.device.mbg.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.device.mbg.domain.entity.Tenant;
import com.device.mbg.mapper.TenantMapper;
import com.device.mbg.service.ITenantService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-28
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

    @Override
    public Tenant getTenantByIdOrName(String tenant) {
        return new LambdaQueryChainWrapper<>(baseMapper).eq(Tenant::getId, tenant).or().eq(Tenant::getName, tenant).one();
    }
}
