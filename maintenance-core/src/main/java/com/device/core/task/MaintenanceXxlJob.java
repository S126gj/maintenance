package com.device.core.task;

import com.device.file.service.IFileService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * xxl-job 动态定时任务
 * @Author: Guoji Shen
 * @Date: 2023/6/27 09:22
 */
@Slf4j
@Component
public class MaintenanceXxlJob {

    @Autowired
    private IFileService fileService;

    @XxlJob("deleteNoSourceFiles")
    private void deleteNoSourceFiles() {
        Long num = fileService.deleteNoSourceFiles();
        log.info("删除无来源单据 {} 条", num);
    }
}