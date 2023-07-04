package com.device.common.constanst;


import org.springframework.stereotype.Component;

/**
 * 常量
 * @Author: Guoji Shen
 * @Date: 2023/6/29 17:09
 */
@Component
public class Constanst {

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

    // 文件格式
    public static String IMAGE = "图片";
    public static String AUDIO = "音频";
    public static String VIDEO = "视频";
    public static String WORD = "文档";
    public static String UNKNOW = "未知";

    /**
     * 默认手机号正则
     */
    public static final String DEFAULT_PATTERN = "^((13[0-9])|(14[01｜(4-9)])|(15[(0-3)|(5-9)])|(16[2567])|(17[0-8])|(18[0-9])|(15[(0-3)|(5-9))]))\\\\d{8}$";

    /**
     * 中国电信号码格式验证 手机段： 133,149,153,173,177,180,181,189,191,199,1349,1410,1700,1701,1702
     **/
    public static final String CHINA_TELECOM_PATTERN = "(?:^(?:\\+86)?1(?:33|49|53|7[37]|8[019]|9[19])\\d{8}$)|(?:^(?:\\+86)?1349\\d{7}$)|(?:^(?:\\+86)?1410\\d{7}$)|(?:^(?:\\+86)?170[0-2]\\d{7}$)";

    /**
     * 中国联通号码格式验证 手机段：130,131,132,145,146,155,156,166,171,175,176,185,186,1704,1707,1708,1709
     **/
    public static final String CHINA_UNICOM_PATTERN = "(?:^(?:\\+86)?1(?:3[0-2]|4[56]|5[56]|66|7[156]|8[56])\\d{8}$)|(?:^(?:\\+86)?170[47-9]\\d{7}$)";

    /**
     * 中国移动号码格式验证
     * 手机段：134,135,136,137,138,139,147,148,150,151,152,157,158,159,178,182,183,184,187,188,195,198,1440,1703,1705,1706
     **/
    public static final String CHINA_MOBILE_PATTERN = "(?:^(?:\\+86)?1(?:3[4-9]|4[78]|5[0-27-9]|78|8[2-478]|98|95)\\d{8}$)|(?:^(?:\\+86)?1440\\d{7}$)|(?:^(?:\\+86)?170[356]\\d{7}$)";

    /**
     * 中国香港
     */
    public static final String CHINA_HANGKONG_PATTERN = "^(00)?(852)[5|6|9]\\d{7}$";

    // 启用
    public static String ENABLE = "Y";
    // 禁用
    public static String UNENABLE = "N";
    // 默认用户密码
    public static final String DEFAULT_PASSWORD = "123456";
    // 日期格式
    public static String FORMAT_YYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_YYMMDD = "yyyy-MM-dd";
}
