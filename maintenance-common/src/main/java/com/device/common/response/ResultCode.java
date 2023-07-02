package com.device.common.response;

/**
 * @Author: Guoji Shen
 * @Date: 2021/11/8 10:36 响应状态码接口
 */
public interface ResultCode {

    /**
     * 操作代码
     */
    int code();

    /**
     * 提示信息
     */
    String msg();
}
