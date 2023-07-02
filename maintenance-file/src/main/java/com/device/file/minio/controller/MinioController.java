package com.device.file.minio.controller;

import com.device.common.utils.R;
import com.device.file.minio.service.MinioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/minio")
@Tag(name = "文件上传")
public class MinioController {

    private final MinioService minioService;

    @Operation(summary = "图片上传")
    @PostMapping(value = "/jpeg")
    public R uploadImg(@RequestBody MultipartFile file) {
        return R.ok().data(minioService.uploadJpeg(file));
    }

    @Operation(summary = "文件上传")
    @PostMapping(value = "/save")
    public R uploadFile(@RequestBody MultipartFile multipartFile) throws Exception {
        return R.ok().data(minioService.uploadFile(multipartFile));
    }

    @Operation(summary = "删除文件")
    @DeleteMapping(value = "/delete")
    public R minioDelete(@Parameter(description = "文件地址") @RequestParam(value = "filePath") String filePath) {
        minioService.delete(filePath);
        return R.ok();
    }

    @Operation(summary = "下载文件")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, String filePath) {
        minioService.download(response, filePath);
    }

}
