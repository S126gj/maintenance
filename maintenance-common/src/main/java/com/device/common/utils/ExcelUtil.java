package com.device.common.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.device.common.handler.FailReasonWriteHandler;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/31 13:41
 */
public class ExcelUtil {

    /**
     * 下载文件
     *
     * @param fileName 文件名
     * @param data     数据
     * @param object   类名
     * @param response
     * @throws IOException
     */
    public static void downloadUsingJson(String fileName, List<?> data, Class<?> object,
        HttpServletResponse response) throws IOException {
        try {
            // 判断头是否含有 failReason 字段
            boolean hasFailReason = Arrays.stream(object.getDeclaredFields())
                .anyMatch(field -> field.isAnnotationPresent(ExcelProperty.class) && "failReason".equals(
                    field.getAnnotation(ExcelProperty.class).value()[0]));
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            // 设置背景颜色
            headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            // 设置头字体
            WriteFont headWriteFont = new WriteFont();
            headWriteFont.setFontHeightInPoints((short) 13);
            headWriteFont.setBold(true);
            headWriteCellStyle.setWriteFont(headWriteFont);
            // 设置头居中
            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            // 内容策略
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            // 设置 水平居中
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            // response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setContentType("application/force-download");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = URLEncoder.encode(String.format("%s %s", fileName, LocalDate.now()), "UTF-8")
                .replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), object)
                .autoCloseStream(Boolean.FALSE)
                .sheet("sheet1")
                .registerWriteHandler(new FailReasonWriteHandler())
                .doWrite(data);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSONUtil.toJsonStr(map));
        }
    }



    /**
     * 下载文件
     *
     * @param fileName 文件名
     * @param data     数据
     * @param object   类名
     * @param response
     * @throws IOException
     */
    public static void downloadFailReason(String fileName, List<?> data, Class<?> object,
        HttpServletResponse response) throws IOException {
        try {
            // 判断头是否含有 failReason 字段
            boolean hasFailReason = Arrays.stream(object.getDeclaredFields())
                .anyMatch(field -> field.isAnnotationPresent(ExcelProperty.class) && "failReason".equals(
                    field.getAnnotation(ExcelProperty.class).value()[0]));
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            // 设置背景颜色
            headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            // 设置头字体
            WriteFont headWriteFont = new WriteFont();
            headWriteFont.setFontHeightInPoints((short) 13);
            headWriteFont.setBold(true);
            headWriteCellStyle.setWriteFont(headWriteFont);
            // 设置头居中
            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            // 内容策略
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            // 设置 水平居中
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            // response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setContentType("application/force-download");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = URLEncoder.encode(String.format("%s %s", fileName, LocalDate.now()), "UTF-8")
                .replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), object)
                .autoCloseStream(Boolean.FALSE)
                .sheet("sheet1")
                .doWrite(data);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSONUtil.toJsonStr(map));
        }
    }

    /**
     * 下载文件
     *
     * @param path     下载到的路径
     * @param fileName 文件名
     * @param data     数据
     * @param object   类名
     */
    public static void download(String path, String fileName, List<?> data, Class<?> object) {
        EasyExcel.write(String.format("%s/%s", path, fileName), object)
            .sheet("sheet1")
            .registerWriteHandler(new FailReasonWriteHandler())
            .doWrite(data);
    }

    /**
     * 读取excel对象
     *
     * @param path     读取的文件路径
     * @param fileName 文件名
     * @param object   类名
     * @param <T>
     * @return
     */
    public static <T> List<T> read(String path, String fileName, Class<T> object) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        List<T> list = new ArrayList<>();
        EasyExcel.read(String.format("%s/%s", path, fileName), object,
            new PageReadListener<T>(dataList -> list.addAll(dataList))).sheet().doRead();
        return list;
    }

    /**
     * 读取excel对象
     *
     * @param inputStream 文件流
     * @param object      类名
     * @param <T>
     * @return
     */
    public static <T> List<T> read(InputStream inputStream, Class<T> object) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        List<T> list = new ArrayList<>();
        EasyExcel.read(inputStream, object, new PageReadListener<T>(dataList -> list.addAll(dataList))).sheet()
            .doRead();
        return list;
    }
}