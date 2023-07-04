package com.device.system.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.device.system.core.entity.Dict;
import com.device.system.core.entity.enums.DictEnum;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-30
 */
public interface IDictService extends IService<Dict> {

    /**
     * 获取字典类型
     * @return
     */
    Map<String, List<String>> getDictByType();

    /**
     * 获取对应字典启用数据
     * @param dict      字典类型
     * @param enable    启用
     * @param page
     * @return
     */
    Map<String, Object> queryAll(DictEnum dict, boolean enable, Page page);

    /**
     * 创建新字典
     * @param dict
     */
    void create(Dict dict);

    /**
     * 修改字典状态
     * @param id        字典id
     * @param status    0->禁用；1->启用
     */
    void updateStatus(String id, Integer status);

    /**
     * 根据字典类型获取所有字典信息
     * @param dictEnum
     */
    List<Dict> queryAll(DictEnum dictEnum);
}
