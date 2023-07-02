package com.device.mbg.handler;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.device.common.constanst.CacheKey;
import com.device.common.utils.RedisUtils;
import com.device.common.utils.SpringContextUtil;
import com.device.mbg.domain.vo.UserInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @Classname PreTenantHandler
 * @Description 租户处理器 -主要实现mybatis-plus https://mp.baomidou.com/guide/tenant.html
 */
@Slf4j
@Component
public class DeviceTenantHandler implements TenantLineHandler {

    /**
     * 多租户标识
     */
    private static final String SYSTEM_TENANT_ID = "tenant_id";

    /**
     * 需要过滤的表
     */
    public static final List<String> IGNORE_TENANT_TABLES = Lists.newArrayList("tenant");

    /**
     * 租户Id
     * @return
     */
    @Override
    public Expression getTenantId() {
        // 从缓存中取出当前请求的租户ID，通过解析器注入到SQL中。
        RedisUtils redisUtils = SpringContextUtil.getBean(RedisUtils.class);
        UserInfo userInfo = (UserInfo) redisUtils.get(String.format("%s%s", CacheKey.TENANT, StpUtil.getLoginId()));
        String tenantId = Optional.ofNullable(userInfo).map(UserInfo::getTenantId).orElse(null);
        log.info("当前租户为:{}", tenantId);
        if (tenantId == null) {
            return new NullValue();
        }
        return new StringValue(tenantId);
    }

    /**
     * 租户字段名
     * @return
     */
    @Override
    public String getTenantIdColumn() {
        return SYSTEM_TENANT_ID;
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return IGNORE_TENANT_TABLES.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
    }
}

