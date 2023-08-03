package com.device.common.constanst;

/**
 * @Author: Guoji Shen
 * @Date: 2023/8/3 09:44
 */
public class DBConstants {

    /**
     * 数据源分组 - 巡店库
     * 这里的tour是yml中的 spring.datasource.dynamic.datasource.master
     */
    public static final String MASTER = "master";

    /**
     * 数据源分组 - 分库分表
     */
    public static final String SHARDING = "sharding";

}
