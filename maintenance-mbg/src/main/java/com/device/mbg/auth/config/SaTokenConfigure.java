package com.device.mbg.auth.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.device.common.utils.R;
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

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
            .addPathPatterns("/**")
            .excludePathPatterns("/favicon.ico")
            .excludePathPatterns("/static/**")
            .excludePathPatterns("/webjars/**")
            .excludePathPatterns("/v3/api-docs/**")
            .excludePathPatterns("/swagger-ui.html/**")
            .excludePathPatterns("/doc.html/**")
            .excludePathPatterns("/tenant/**")
            .excludePathPatterns("/sso/**")
        ;
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