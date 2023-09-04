package com.device.file.controller;

import com.device.common.utils.R;
import com.device.file.component.minio.service.MinioService;
import com.device.file.entity.enums.FileResouceTypeEnum;
import com.device.file.service.IFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 * 文件信息表 前端控制器
 * </p>
 *
 * @author GuojiShen
 * @since 2023-07-03
 */
@Slf4j
@RestController
@RequestMapping("/file")
@Tag(name = "文件管理")
public class FileController {

    @Autowired
    private MinioService minioService;
    @Autowired
    private IFileService fileService;

    @Operation(summary = "客户图片上传")
    @PostMapping(value = "/customer/jpeg")
    public R uploadImgCustomer(@RequestBody MultipartFile file, @RequestParam(required = false) FileResouceTypeEnum resouceType) {
        String id = fileService.uploadImgCustomer(file, resouceType);
        return R.ok().data(fileService.getById(id));
    }

    @Operation(summary = "客户文件上传")
    @PostMapping(value = "/customer/save")
    public R uploadFileCustomer(@RequestBody MultipartFile file, @RequestParam(required = false) FileResouceTypeEnum resouceType) {
        String id = fileService.uploadFileCustomer(file, resouceType);
        return R.ok().data(fileService.getById(id));
    }

    @Operation(summary = "前台图片上传")
    @PostMapping(value = "/user/jpeg")
    public R uploadImgUser(@RequestBody MultipartFile file, @RequestParam(required = false) FileResouceTypeEnum resouceType) {
        String id = fileService.uploadImgUser(file, resouceType);
        return R.ok().data(fileService.getById(id));
    }

    @Operation(summary = "前台文件上传")
    @PostMapping(value = "/user/save")
    public R uploadFileUser(@RequestBody MultipartFile file, @RequestParam(required = false) FileResouceTypeEnum resouceType) {
        String id = fileService.uploadFileUser(file, resouceType);
        return R.ok().data(fileService.getById(id));
    }

    @Operation(summary = "删除文件")
    @DeleteMapping(value = "/delete")
    public R minioDelete(@Parameter(description = "文件id") @RequestParam(value = "fileId") String fileId) {
        fileService.delete(fileId);
        return R.ok();
    }

    @Operation(summary = "下载文件")
    @GetMapping(value = "/download")
    public void download(@Parameter(description = "文件地址") @RequestParam(value = "filePath") String filePath,
        HttpServletResponse response) {
        minioService.download(response, filePath);
    }
}