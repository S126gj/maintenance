package com.device.file.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.device.file.entity.File;
import com.device.file.entity.criteria.UploadRecordCriteria;
import com.device.file.entity.enums.FileResouceTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 文件信息表 服务类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-07-03
 */
public interface IFileService extends IService<File> {

    /**
     * 查询全部文件记录
     * @param criteria
     * @param page
     * @return
     */
    Map<String, Object> queryAll(UploadRecordCriteria criteria, Page page);

    /**
     * 客户上传图片
     * @param file
     * @param resouceType
     * @return
     */
    String uploadImgCustomer(MultipartFile file, FileResouceTypeEnum resouceType);

    /**
     * 客户上传文件
     * @param file
     * @param resouceType
     * @return
     */
    String uploadFileCustomer(MultipartFile file, FileResouceTypeEnum resouceType);

    /**
     * 用户上传图片
     * @param file
     * @param resouceType
     * @return
     */
    String uploadImgUser(MultipartFile file, FileResouceTypeEnum resouceType);

    /**
     * 用户上传文件
     * @param file
     * @param resouceType
     * @return
     */
    String uploadFileUser(MultipartFile file, FileResouceTypeEnum resouceType);

    /**
     * 删除文件
     * @param fileId    文件id
     */
    void delete(String fileId);

    /**
     * 删除无来源单据
     * @return  删除条数
     */
    Long deleteNoSourceFiles();
}