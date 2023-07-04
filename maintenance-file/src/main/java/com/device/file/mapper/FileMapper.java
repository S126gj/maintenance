package com.device.file.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.device.file.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.device.file.entity.criteria.UploadRecordCriteria;
import com.device.file.entity.vo.UploadRecordVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文件信息表 Mapper 接口
 * </p>
 *
 * @author GuojiShen
 * @since 2023-07-03
 */
public interface FileMapper extends BaseMapper<File> {

    /**
     * 查询全部文件上传记录
     * @param criteria
     * @param page
     * @return
     */
    IPage<UploadRecordVO> queryAll(@Param("criteria") UploadRecordCriteria criteria, Page page);
}
