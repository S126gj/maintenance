package com.device.file.component.minio.service;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;

public interface MinioService {

    /**
     * 上传图片
     * @param file
     * @return
     */
    String uploadJpeg(MultipartFile file);

    /**
     * 上传文件
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);

    String uploadFile(MultipartFile file, String fileName);

    String uploadFile(String minioFilePath, InputStream stream);

    /**
     * 下载文件
     * @param response
     * @param filePath
     */
    void download(HttpServletResponse response, String filePath);

    /**
     * 预览JPEG
     * @param response
     * @param filePath
     */
    void viewJpeg(HttpServletResponse response, String filePath);

    /**
     * 删除
     * @param filePath
     */
    void delete(String filePath);

    InputStream getInputStream(String filePath);

    /**
     * 上传直接下载的文件（不预览）
     * @param multipartFile
     * @return
     */
    String uploadFileDownload(MultipartFile multipartFile);

    /**
     * 上传文件(无需SessionLocal)
     * @param file
     * @param fileName
     * @return
     */
    String uploadFileWithOutDB(MultipartFile file, String fileName);
    /**
     * 上传文件(无需SessionLocal)
     * @param file
     * @return
     */
    String uploadFileWithOutDB(MultipartFile file);
}
