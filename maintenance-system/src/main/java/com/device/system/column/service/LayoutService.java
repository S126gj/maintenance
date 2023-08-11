package com.device.system.column.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.device.system.column.entity.Layout;
import com.device.system.column.entity.dto.LayoutDTO;

import java.util.List;


/**
 * <p>
 * 显示列表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-28
 */
public interface LayoutService extends IService<Layout> {
    /**
     * 获取显示列集合
     * @return
     */
    List<LayoutDTO> select();

    /**
     * 编辑
     * @param resources /
     */
    void update(LayoutDTO resources);

    /**
     * 根据id查询
     * @return
     */
    LayoutDTO findById(String id);
}
