package com.device.common.handler;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.device.common.utils.StringUtil;

import java.util.List;

/**
 * list - string 转换
 * @Author: Guoji Shen
 * @Date: 2023/8/1 10:16
 */
public class ListStringConverter implements Converter<List> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return List.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public List convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return StringUtil.convertToList(cellData.getStringValue());
    }

    @Override
    public WriteCellData<?> convertToExcelData(List value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return new WriteCellData<>(StringUtil.convertStr(value));
    }
}