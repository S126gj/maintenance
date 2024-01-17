package com.device.common.handler;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellUtil;

/**
 * @Author: Guoji Shen
 * @Date: 2023/12/14 14:06
 */
public class FailReasonWriteHandler implements CellWriteHandler {

    private static final String FIELD_NAME = "failReason"; // 需要隐藏的字段名

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
        Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
        // 隐藏 failReason 列
        if (isHead && "failReason".equals(head.getFieldName())) {
            writeSheetHolder.getSheet().setColumnWidth(columnIndex, 60);
        }

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
        Head head, Integer relativeRowIndex, Boolean isHead) {
        // 隐藏 failReason 列
        if (isHead && FIELD_NAME.equals(head.getFieldName())) {
            writeSheetHolder.getSheet().setColumnHidden(cell.getColumnIndex(), true);
        }
        //居中
        CellUtil.setAlignment(cell, HorizontalAlignment.CENTER);
        CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
    }
}