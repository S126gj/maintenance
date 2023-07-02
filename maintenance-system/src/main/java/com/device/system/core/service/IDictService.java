package com.device.system.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.device.system.core.entity.Dict;
import com.device.system.core.entity.enums.DictEnum;

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
     * 获取对应字典启用数据
     * @param dict      字典类型
     * @param enable    启用
     * @param page
     * @return
     */
    Map<String, Object> queryAll(DictEnum dict, boolean enable, Page page);

    void create(Dict dict);
}
