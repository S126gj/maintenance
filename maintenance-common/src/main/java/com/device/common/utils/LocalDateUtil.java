package com.device.common.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.device.common.constanst.Constanst;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

/**
 * 日期工具类
 * @Author: Guoji Shen
 * @Date: 2023/6/29 08:08
 */
public class LocalDateUtil extends LocalDateTimeUtil {

    /**
     * Date 转换 LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime dateTransLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime 转换 Date
     * @param date
     * @return
     */
    public static Date localDateTimeTransDate(LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 添加相应秒数
     * @param date      日期
     * @param number    秒数
     * @return
     */
    public static LocalDateTime addSecond(LocalDateTime date, int number) {
        date = Objects.isNull(date) ? LocalDateTime.now() : date;
        return date.plusSeconds(number);
    }

    /**
     * 添加相应分钟数
     * @param date      日期
     * @param number    分钟数
     * @return
     */
    public static LocalDateTime addMinute(LocalDateTime date, int number) {
        date = Objects.isNull(date) ? LocalDateTime.now() : date;
        return date.plusMinutes(number);
    }

    /**
     * 添加相应分钟数
     * @param date      日期
     * @param number    分钟数
     * @return
     */
    public static LocalDateTime addHour(LocalDateTime date, int number) {
        date = Objects.isNull(date) ? LocalDateTime.now() : date;
        return date.plusHours(number);
    }

    /**
     * 添加相应天数
     * @param date      日期
     * @param number    天数
     * @return
     */
    public static LocalDateTime addDay(LocalDateTime date, int number) {
        date = Objects.isNull(date) ? LocalDateTime.now() : date;
        return date.plusDays(number);
    }

    /**
     * 添加相应月数
     * @param date      日期
     * @param number    月数
     * @return
     */
    public static LocalDateTime addMonth(LocalDateTime date, int number) {
        date = Objects.isNull(date) ? LocalDateTime.now() : date;
        return date.plusMonths(number);
    }

    /**
     * 添加相应年数
     * @param date      日期
     * @param number    年数
     * @return
     */
    public static LocalDateTime addYear(LocalDateTime date, int number) {
        date = Objects.isNull(date) ? LocalDateTime.now() : date;
        return date.plusYears(number);
    }

    /**
     * 减少相应秒数
     * @param date      日期
     * @param number    秒数
     * @return
     */
    public static LocalDateTime subSecond(LocalDateTime date, int number) {
        date = Objects.isNull(date) ? LocalDateTime.now() : date;
        return date.minusSeconds(number);
    }

    /**
     * 减少相应分钟数
     * @param date      日期
     * @param number    分钟数
     * @return
     */
    public static LocalDateTime subMinute(LocalDateTime date, int number) {
        date = Objects.isNull(date) ? LocalDateTime.now() : date;
        return date.minusMinutes(number);
    }

    /**
     * 减少相应分钟数
     * @param date      日期
     * @param number    分钟数
     * @return
     */
    public static LocalDateTime subHour(LocalDateTime date, int number) {
        date = Objects.isNull(date) ? LocalDateTime.now() : date;
        return date.minusHours(number);
    }

    /**
     * 减少相应天数
     * @param date      日期
     * @param number    天数
     * @return
     */
    public static LocalDateTime subDay(LocalDateTime date, int number) {
        date = Objects.isNull(date) ? LocalDateTime.now() : date;
        return date.minusDays(number);
    }

    /**
     * 减少相应月数
     * @param date      日期
     * @param number    月数
     * @return
     */
    public static LocalDateTime subMonth(LocalDateTime date, int number) {
        date = Objects.isNull(date) ? LocalDateTime.now() : date;
        return date.minusMonths(number);
    }

    /**
     * 减少相应年数
     * @param date      日期
     * @param number    年数
     * @return
     */
    public static LocalDateTime subYear(LocalDateTime date, int number) {
        date = Objects.isNull(date) ? LocalDateTime.now() : date;
        return date.minusYears(number);
    }

    /**
     * 计算两个日期相差的秒数
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @return
     */
    public static Long calcSeconds(LocalDateTime beginTime, LocalDateTime endTime) {
        return Math.abs(ChronoUnit.SECONDS.between(beginTime, endTime));
    }

    /**
     * 计算两个日期相差的分钟数
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @return
     */
    public static Long calcMinute(LocalDateTime beginTime, LocalDateTime endTime) {
        return Math.abs(ChronoUnit.MINUTES.between(beginTime, endTime));
    }

    /**
     * 计算两个日期相差的小时数
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @return
     */
    public static Long calcHour(LocalDateTime beginTime, LocalDateTime endTime) {
        return Math.abs(ChronoUnit.HOURS.between(beginTime, endTime));
    }

    /**
     * 计算两个日期相差的天数
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @return
     */
    public static Long calcDay(LocalDateTime beginTime, LocalDateTime endTime) {
        return Math.abs(ChronoUnit.DAYS.between(beginTime, endTime));
    }

    /**
     * 计算两个日期相差的月数
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @return
     */
    public static Long calcMonth(LocalDateTime beginTime, LocalDateTime endTime) {
        return Math.abs(ChronoUnit.MONTHS.between(beginTime, endTime));
    }

    /**
     * 计算两个日期相差的年数
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @return
     */
    public static Long calcYear(LocalDateTime beginTime, LocalDateTime endTime) {
        return Math.abs(ChronoUnit.YEARS.between(beginTime, endTime));
    }

    /**
     * 重新设置时分秒
     * @param localDateTime     修改的时间
     * @param hour              时
     * @param minute            分
     * @param second            秒
     * @param nano              纳秒
     * @return
     */
    public static LocalDateTime transHourMinuteSecondNano(LocalDateTime localDateTime, int hour, int minute, int second, int nano) {
        return localDateTime.withHour(hour).withMinute(minute).withSecond(second).withNano(nano);
    }

    /**
     * LocalDate转String
     * @param localDate
     * @return
     */
    public static String format(LocalDate localDate){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(Constanst.FORMAT_YYMMDD);
        return df.format(localDate);
    }

    /**
     * LocalDateTime转String
     * @param localDateTime
     * @return
     */
    public static String format(LocalDateTime localDateTime){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(Constanst.FORMAT_YYMMDD_HHMMSS);
        return df.format(localDateTime);
    }

    /**
     * String转LocalDate
     * @param str
     * @return
     */
    public static LocalDate parseLocalDate(String str){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(Constanst.FORMAT_YYMMDD);
        return LocalDate.parse(str, df);
    }

    /**
     * String转LocalDateTime
     * @param str
     * @return
     */
    public static LocalDateTime parseLocalDateTime(String str){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(Constanst.FORMAT_YYMMDD_HHMMSS);
        return LocalDateTime.parse(str, df);
    }

    /**
     * 获取当天开始时间
     * @return
     */
    public static LocalDateTime getTodayBegin() {
        return LocalDateTime.now().with(LocalTime.MIN);
    }

    /**
     * 获取当天结束时间
     * @return
     */
    public static LocalDateTime getTodayEnd() {
        return LocalDateTime.now().with(LocalTime.MAX);
    }
}