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
     * 角色
     */
    String ROLE = PROJECT + ":" + "role:";

    /**
     * 文件
     */
    String FILE = PROJECT + ":" + "file:";

    /**
     * 字典
     */
    String DICT = PROJECT + ":" + "dict:";

    /**
     * 经销商
     */
    String DEALER = PROJECT + ":" + "dealer:";
}
