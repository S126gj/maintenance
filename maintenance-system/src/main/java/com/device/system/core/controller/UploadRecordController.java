package com.device.system.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.device.common.utils.R;
import com.device.file.entity.criteria.UploadRecordCriteria;
import com.device.file.service.IFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/4 09:56
 */
@RestController
@RequestMapping("/upload-record")
@Tag(name = "文件上传记录")
public class UploadRecordController {

    @Autowired
    private IFileService fileService;

    @Operation(summary = "获取文件上传记录")
    @GetMapping("/queryAll")
    public R queryAll(UploadRecordCriteria criteria, Page page) {
        return R.ok().data(fileService.queryAll(criteria, page));
    }
}