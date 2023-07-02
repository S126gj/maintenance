package com.device.common.response;

import java.io.Serializable;

/**
 * @Author: Guoji Shen
 * @Date: 2021/11/8 10:36
 */
public interface Model extends Serializable {

    /**
     * 检查属性
     *
     * @param isThrow 是否抛出异常
     * @return 结果
     */
    default boolean check(boolean isThrow) {
        return true;
    }

}