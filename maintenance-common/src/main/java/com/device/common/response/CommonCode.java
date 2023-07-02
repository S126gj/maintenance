package com.device.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * @Author: Guoji Shen
 * @Date: 2021/11/8 12:24 通用状态码，采用http协议
 */
@AllArgsConstructor
public enum CommonCode implements ResultCode {
    /**
     * 操作成功
     */
    SUCCESS(HttpStatus.OK.value(), "操作成功"),
    /**
     * 频繁
     */
    BUSY(HttpStatus.BAD_REQUEST.value(), "操作过于频繁"),
    /**
     * 业务异常
     */
    BIZ_FAILURE(HttpStatus.BAD_REQUEST.value(), "业务异常"),

    /**
     * 请求未授权
     */
    UN_AUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "请求未授权"),

    /**
     * 404 没找到请求
     */
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "404 没找到请求"),

    /**
     * 消息不能读取
     */
    MSG_NOT_READABLE(HttpStatus.BAD_REQUEST.value(), "消息不能读取"),

    /**
     * 不支持当前请求方法
     */
    METHOD_NOT_SUPPORTED(HttpStatus.METHOD_NOT_ALLOWED.value(), "不支持当前请求方法"),

    /**
     * 不支持当前媒体类型
     */
    MEDIA_TYPE_NOT_SUPPORTED(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "不支持当前媒体类型"),

    /**
     * 请求被拒绝
     */
    REQ_REJECT(HttpStatus.FORBIDDEN.value(), "请求被拒绝"),

    /**
     * 服务器异常
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常"),

    /**
     * 缺少必要的请求参数
     */
    PARAM_MISS(HttpStatus.BAD_REQUEST.value(), "缺少必要的请求参数"),

    /**
     * 请求参数类型错误
     */
    PARAM_TYPE_ERROR(HttpStatus.BAD_REQUEST.value(), "请求参数类型错误"),

    /**
     * 请求参数绑定错误
     */
    PARAM_BIND_ERROR(HttpStatus.BAD_REQUEST.value(), "请求参数绑定错误"),

    /**
     * 参数校验失败
     */
    PARAM_VALID_ERROR(HttpStatus.BAD_REQUEST.value(), "参数校验失败"),

    /**
     * 签名验证失败
     */
    SIGN_VALID_ERROR(HttpStatus.UNAUTHORIZED.value(), "验签失败"),

    /**
     * 白名单验证失败
     */
    WHITE_IP_VALID_ERROR(HttpStatus.UNAUTHORIZED.value(), "IP白名单验证失败"),

    /**
     * 密码验证失败
     */
    TENANT_VALID_ERROR(HttpStatus.UNAUTHORIZED.value(), "租户验证失败"),

    /**
     * 密码验证失败
     */
    PASSWORD_VALID_ERROR(HttpStatus.UNAUTHORIZED.value(), "密码验证失败"),

    ;

    @Getter
    private int code;
    @Getter
    private String msg;

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }

    public static ResultCode of(int code) {
        for (CommonCode c : values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }
}