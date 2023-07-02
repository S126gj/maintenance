package com.device.system.task;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * xxl-job 动态定时任务
 * @Author: Guoji Shen
 * @Date: 2023/6/27 09:22
 */
@Slf4j
@Component
public class MaintenanceXxlJob {

    @XxlJob("cancelTimeOutOrder")
    private void cancelTimeOutOrder() {
        Integer count = 0;
        log.info("取消订单，并根据sku编号释放锁定库存，取消订单数量：{}", count);
    }
}