package com.device.core.run;

import com.device.mbg.service.ITenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动调用
 * @Author: Guoji Shen
 * @Date: 2023/9/26 15:38
 */
@Slf4j
@Component
public class MaintenanceCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ITenantService tenantService;

    @Override
    public void run(String... args) {
        log.info("==========================开始更新租户信息==========================");
        // todo
        log.info("==========================租户信息更新完毕==========================");
    }
}
