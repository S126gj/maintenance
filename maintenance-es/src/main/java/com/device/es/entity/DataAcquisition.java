package com.device.es.entity;

import lombok.Data;
import org.dromara.easyes.annotation.IndexField;
import org.dromara.easyes.annotation.IndexId;
import org.dromara.easyes.annotation.IndexName;
import org.dromara.easyes.annotation.rely.FieldType;
import org.dromara.easyes.annotation.rely.IdType;

import java.util.Date;

/**
 * 数据接收
 * @Author: Guoji Shen
 * @Date: 2023/7/14 09:34
 */
@Data
@IndexName("test")
public class DataAcquisition {

    /**
     * es中的唯一id(无需赋值)
     */
    @IndexId(type = IdType.NONE)
    private String id;

    /**
     * 设备编号
     */
    @IndexField(value = "boxCode")
    private String boxCode;

    /**
     * 接收原始数据
     */
    @IndexField(value = "originData", fieldType = FieldType.TEXT) //指定类型
    private String originData;

    /**
     * 创建时间
     */
    @IndexField(value = "createTime", fieldType = FieldType.DATE) //指定类型
    private Date createTime;
}