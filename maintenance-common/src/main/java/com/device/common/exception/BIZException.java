package com.device.common.exception;

import com.device.common.response.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BIZException extends RuntimeException {

    private Integer code;// 状态码
    private String msg;// 异常信息

    public BIZException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BIZException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public BIZException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BIZException(ResultCode statusCode, String message) {
        super(message);
        this.code = statusCode.code();
        this.msg = statusCode.msg() + ":" + message;
    }

    public BIZException(int code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

}
