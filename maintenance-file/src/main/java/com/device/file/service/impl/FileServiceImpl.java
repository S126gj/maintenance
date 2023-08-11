package com.device.file.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.device.common.constanst.CacheKey;
import com.device.common.exception.Checker;
import com.device.common.exception.Errors;
import com.device.common.utils.Arith;
import com.device.common.utils.RedisUtils;
import com.device.file.component.minio.service.MinioService;
import com.device.file.entity.File;
import com.device.file.entity.criteria.UploadRecordCriteria;
import com.device.file.entity.enums.FileResouceTypeEnum;
import com.device.file.entity.enums.FileTypeEnum;
import com.device.file.entity.vo.UploadRecordVO;
import com.device.file.mapper.FileMapper;
import com.device.file.service.IFileService;
import com.device.mbg.domain.vo.UserInfo;
import com.device.mbg.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 文件信息表 服务实现类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-07-03
 */
@Slf4j
@Service
@CacheConfig(cacheNames = CacheKey.FILE)
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private MinioService minioService;

    @Override
    @Cacheable(key = "#root.target.getKey()")
    public Map<String, Object> queryAll(UploadRecordCriteria criteria, Page page) {
        IPage<UploadRecordVO> iPage = baseMapper.queryAll(criteria, page);
        return PageUtil.toPage(iPage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String uploadImg(MultipartFile multipartFile, FileResouceTypeEnum resouceType) {
        UserInfo userInfo = (UserInfo) redisUtils.get(String.format("%s%s", CacheKey.TENANT, StpUtil.getLoginId()));
        String path = minioService.uploadJpeg(multipartFile);
        File file = File.builder()
            .path(path)
            .size(formatFileSize(multipartFile.getSize()))
            .resourceType(resouceType)
            .fileType(multipartFile.getContentType())
            .type(FileTypeEnum.JPEG)
            .name(multipartFile.getOriginalFilename())
            .uploadMan(userInfo.getUsername())
            .build();
        baseMapper.insert(file);
        delCaches();
        return file.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String uploadFile(MultipartFile multipartFile, FileResouceTypeEnum resouceType) {
        UserInfo userInfo = (UserInfo) redisUtils.get(String.format("%s%s", CacheKey.TENANT, StpUtil.getLoginId()));
        String path = minioService.uploadFile(multipartFile);
        File file = File.builder()
            .path(path)
            .size(formatFileSize(multipartFile.getSize()))
            .resourceType(resouceType)
            .fileType(multipartFile.getContentType())
            .type(FileTypeEnum.getFileTypeByType(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+ 1)))
            .name(multipartFile.getOriginalFilename())
            .uploadMan(userInfo.getUsername())
            .build();
        log.info("[FileServiceImpl][uploadFile] ");
        baseMapper.insert(file);
        delCaches();
        return file.getId();
    }

    public static void main(String[] args) {
        String originName = "新建文本文档.txt";
        System.out.println(originName.substring(originName.lastIndexOf(".")+ 1));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String fileId) {
        File file = baseMapper.selectById(fileId);
        Checker.ifNullThrow(file, () -> Errors.BIZ.exception("未查询到此文件,无法删除"));
        minioService.delete(file.getPath());
        baseMapper.deleteById(fileId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long deleteNoSourceFiles() {
        LambdaQueryWrapper<File> wrapper = new LambdaQueryWrapper<File>().isNull(File::getResourceType);
        Long num = baseMapper.selectCount(wrapper);
        baseMapper.delete(wrapper);
        return num;
    }

    /**
     * B->M
     * @param fileS
     * @return
     */
    public Double formatFileSize(long fileS) {
        Double mb = (double) fileS / 1024D / 1024D;
        return Arith.halfUp(2, mb);
    }


    private void delCaches(){
        redisUtils.del(String.format("%s%s", CacheKey.FILE, getKey()));
    }
    public String getKey(){
        return  String.format("%s:", StpUtil.getLoginId());
    }
}