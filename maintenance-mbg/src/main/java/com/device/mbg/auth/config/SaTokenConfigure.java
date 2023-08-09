package com.device.mbg.auth.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.device.common.utils.R;
import com.device.mbg.auth.util.StpCustomerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/26 13:38
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(SaTokenConfigure.class);

    // 拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handle -> {

                // 只有登录 user 用户才可以访问的接口
                SaRouter.match(
                    "/file/**",
                    "/combo/layout/**",
                    "/acl/layout/**",
                    "/acl/layout-user/**",
                    "/menu/**",
                    "/role/**",
                    "/user/**",
                    "/dict/**",
                    "/machine/**"
                ).check(r -> StpUtil.checkLogin());

                // 只有登录 customer 客户才可以访问的接口
                SaRouter.match(
                    "/customer-client/**"
                ).check(r -> StpCustomerUtil.checkLogin());

                // 只有 user 用户与 customer 客户同时登录才可以访问的接口
                // SaRouter.match("/art/getInfo").check(r -> {
                //     StpUtil.checkLogin();
                //     StpCustomerUtil.checkLogin();
                // });

                // 只需要登录 user 用户 和 customer 客户 任意一个，就能访问的接口：
                // SaRouter.match("/art/getInfo").check(r -> {
                //     if(StpUtil.isLogin() == false && StpCustomerUtil.isLogin() == false) {
                //         throw new SaTokenException("请登录后再访问接口");
                //     }
                // });

            }))
            .addPathPatterns("/**")
            .excludePathPatterns(
                // api接口文档
                "/favicon.ico",
                "/static/**",
                "/webjars/**",
                "/v3/api-docs/**",
                "/swagger-ui.html/**",
                "/doc.html/**",
                // 租户
                "/tenant/**",
                // 用户登录
                "/sso/**",
                // 客户登录
                "/customer/sso/**"
            );
    }

    /**
     * 注册 [Sa-Token全局过滤器] 解决跨域问题
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
            // 拦截与排除 path
            .addInclude("/**").addExclude("/favicon.ico")

            // 全局认证函数
            .setAuth(obj -> {

            })

            // 异常处理函数
            .setError(e -> {
                return R.error(e.getMessage());
            })

            // 前置函数：在每次认证函数之前执行
            .setBeforeAuth(obj -> {
                // ---------- 设置跨域响应头 ----------
                SaHolder.getResponse()
                    // 允许指定域访问跨域资源
                    .setHeader("Access-Control-Allow-Origin", "*")
                    // 允许所有请求方式
                    .setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT")
                    // 有效时间
                    .setHeader("Access-Control-Max-Age", "3600")
                    // 允许的header参数
                    .setHeader("Access-Control-Allow-Headers", "*");

                // 如果是预检请求，则立即返回到前端
                SaRouter.match(SaHttpMethod.OPTIONS)
                    .free(r -> log.info("--------OPTIONS预检请求，不做处理"))
                    .back();
            })
            ;
    }
}