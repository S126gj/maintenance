package com.device.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池
 * @Author: Guoji Shen
 * @Date: 2022/1/20 9:04
 */
@Configuration
public class ThreadPoolConfig {

    public static ThreadPoolTaskExecutor executor;

    public static ThreadPoolTaskScheduler taskScheduler;

    @Value("${task.pool.core-pool-size}")
    private Integer CORE_POOL_SIZE;
    @Value("${task.pool.max-pool-size}")
    private Integer MAX_POOL_SIZE;
    @Value("${task.pool.queue-capacity}")
    private Integer QUEUE_CAPACITY;
    @Value("${task.pool.keep-alive-seconds}")
    private Integer KEEP_ALIVE_SECONDS;

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(CORE_POOL_SIZE);
        // 最大线程数
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        // 阻塞队列长度
        executor.setQueueCapacity(QUEUE_CAPACITY);
        // 空闲线程最大存活时间
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        // 拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(ThreadPoolTaskExecutor executor) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler = threadPoolTaskScheduler;
        threadPoolTaskScheduler.setThreadFactory(executor);
        return threadPoolTaskScheduler;
    }
}