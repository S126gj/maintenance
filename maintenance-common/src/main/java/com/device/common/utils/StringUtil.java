package com.device.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/5 10:52
 */
public class StringUtil extends StrUtil {


    /**
     * 空字符串
     */
    private static final String NULLSTR = "";

    /**
     * 忽略大小写，全半角对比
     *
     * @return
     */
    public static Boolean equals(String str1, String str2) {
        return Convert.toDBC(str1).equalsIgnoreCase(Convert.toDBC(str2));
    }

    /**
     * 转换list，逗号分割
     *
     * @param list
     * @return
     */
    public static String convertStr(List<String> list) {
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        return String.join(",", list);
    }

    public static String conToStr(List<String> list) {
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        StringJoiner stringJoiner = new StringJoiner(",", "(", ")");
        list.forEach(item -> stringJoiner.add(new StringBuilder().append("'").append(item).append("'")));
        return stringJoiner.toString();
    }

    /**
     * 逗号分割的字符串转换为数组 如 "1,2,3,4,5,6"
     *
     * @param str
     * @return
     */
    public static List<String> convertToList(String str) {
        if (StrUtil.isBlank(str)) {
            return null;
        }
        return Arrays.asList(Convert.toStrArray(str));
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @return 结果
     */
    public static String substring(final String str, int start) {
        if (str == null) {
            return NULLSTR;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return NULLSTR;
        }

        return str.substring(start);
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @param end   结束
     * @return 结果
     */
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return NULLSTR;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return NULLSTR;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }
}