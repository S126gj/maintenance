package com.device.system.column.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.device.system.column.entity.LayoutDetails;
import com.device.system.column.entity.dto.LayoutDTO;
import com.device.system.column.entity.dto.LayoutDetailsDTO;

import java.util.List;


/**
 * <p>
 * 显示列表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-28
 */
public interface LayoutDetailsService extends IService<LayoutDetails> {
    /**
     * 根据pid查询
     */
    List<LayoutDetailsDTO> queryAllNoDocId();
    /**
     * 根据pid查询
     */
    List<LayoutDetailsDTO> findByPid(String pid);

    /**
     * 保存
     */
    void save(LayoutDTO dto);

    /**
     * 保存
     * @param detailsDto
     */
    void save(LayoutDetailsDTO detailsDto);
}
