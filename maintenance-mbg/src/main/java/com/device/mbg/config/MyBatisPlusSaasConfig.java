package com.device.mbg.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.device.mbg.handler.DeviceTenantHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis 配置类
 *
 * @Author: Guoji Shen
 * @Date: 2022/8/5 09:57
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.device.mbg.mapper", "com.device.file.mapper", "com.device.core.column.mapper",
    "com.device.core.user.mapper", "com.device.core.customer.mapper"})
public class MyBatisPlusSaasConfig {

    /**
     * 新多租户插件配置,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存万一出现问题
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // TenantLineInnerInterceptor
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new DeviceTenantHandler()));
        // 如果用了分页插件注意先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}