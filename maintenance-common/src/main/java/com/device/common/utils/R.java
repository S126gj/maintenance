package com.device.common.utils;

import com.device.common.exception.ApiError;
import com.device.common.response.CommonCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


/**
 * 统一返回结果的类
 */
@Getter
@Setter
@NoArgsConstructor
public class R {

    @Schema(description = "是否成功")
    private Boolean success;

    @Schema(description = "返回码")
    private Integer code;

    @Schema(description = "返回消息")
    private String message;

    @Schema(description = "返回数据")
    private Map<String, Object> data = new HashMap<>();


    public static R ok() {
        return ok("成功");
    }

    public static R ok(String msg) {
        R r = new R();
        r.setSuccess(true);
        r.setCode(CommonCode.SUCCESS.getCode());
        r.setMessage(msg);
        return r;
    }

    public static R ok(Integer code) {
        R r = new R();
        r.setSuccess(true);
        r.setCode(code);
        r.setMessage("成功");
        return r;
    }

    public static R error() {
        return error("失败");
    }

    public static R error(String msg) {
        return error(CommonCode.BIZ_FAILURE.getCode(), msg);
    }

    public static R error(Integer code, String msg) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public static R error(ApiError error) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(error.getStatus());
        r.setMessage(error.getMessage());
        return r;
    }

    public R put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    public R data(Object value) {
        this.data.put("content", value);
        return this;
    }
}

