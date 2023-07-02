package com.device.common.exception;

import com.device.common.response.CommonCode;
import com.device.common.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常类型枚举
 *
 * @Author: 沈国纪
 * @Date: 2021/11/8 10:03
 */
@AllArgsConstructor
public enum Errors {
    /**
     * 系统
     */
    SYSTEM(CommonCode.INTERNAL_SERVER_ERROR.code(), CommonCode.INTERNAL_SERVER_ERROR.msg()),
    /**
     * 参数
     */
    PARAM(CommonCode.PARAM_VALID_ERROR.code(), CommonCode.PARAM_VALID_ERROR.msg()),
    /**
     * 业务
     */
    BIZ(CommonCode.BIZ_FAILURE.code(), CommonCode.BIZ_FAILURE.msg());

    /**
     * 码
     */
    @Getter
    private int code;
    /**
     * 信息
     */
    @Getter
    private String message;

    /**
     * 异常
     *
     * @param code .
     * @return
     */
    public BIZException exception(ResultCode code) {
        return new BIZException(code.code(), code.msg());
    }

    /**
     * 异常
     *
     * @param msg .
     * @return
     */
    public BIZException exception(String msg) {
        return new BIZException(code, msg);
    }

    /**
     * 异常
     *
     * @param code .
     * @param msg  .
     * @return
     */
    public BIZException exception(ResultCode code, String msg) {
        return new BIZException(code, msg);
    }

    /**
     * 异常
     *
     * @param code .
     * @param msg  .
     * @return
     */
    public BIZException exception(int code, String msg) {
        return new BIZException(code, msg);
    }

}