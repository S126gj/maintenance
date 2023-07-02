package com.device.common.utils;

import org.springframework.stereotype.Component;

/**
 * 常量
 * @Author: Guoji Shen
 * @Date: 2023/6/26 17:22
 */
@Component
public class Constants {


    public static String ADMIN = "管理员";

    /** 默认验证码过期时间 */
    public static Long DEFAULT_AUTH_CODE_EXPIRE = 5L;

    /** 默认验证码过期时间 */
    public static String DEFAULT_AUTH_CODE_EXPIRE_STR = String.valueOf(DEFAULT_AUTH_CODE_EXPIRE);

    /**
     * UTF-8 字符集
     */
    public static String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

}
