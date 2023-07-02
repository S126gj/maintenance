package com.device.common.constanst;

/**
 * 缓存key
 *
 * @Author: Guoji Shen
 * @Date: 2023/6/29 09:17
 */
public interface CacheKey {

    /**
     * 项目
     */
    String PROJECT = "maintenance";

    /**
     * 租户
     */
    String TENANT = PROJECT + ":" + "tenant:";

    /**
     * 布局
     */
    String LAYOUT = PROJECT + ":" + "layout:";

    /**
     * 字典
     */
    String DICT = PROJECT + ":" + "dict:";
}
