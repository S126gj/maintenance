package com.device.file.entity.enums;

import com.device.common.constanst.Constanst;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/4 14:47
 */
@AllArgsConstructor
public enum FileTypeEnum {
    JPG("jpg", Constanst.IMAGE),
    JPEG("jpeg", Constanst.IMAGE),
    PNG("png", Constanst.IMAGE),
    BMP("bmp", Constanst.IMAGE),
    GIF("gif", Constanst.IMAGE),
    PSD("psd", Constanst.IMAGE),
    TIF("tif", Constanst.IMAGE),
    SVG("svg", Constanst.IMAGE),
    webp("webp", Constanst.IMAGE),
    MP3("mp3", Constanst.AUDIO),
    MP4("mp4", Constanst.VIDEO),
    MPEG("mpeg", Constanst.VIDEO),
    WMV("wmv", Constanst.VIDEO),
    AVI("avi", Constanst.VIDEO),
    MOV("mov", Constanst.VIDEO),
    FLV("flv", Constanst.VIDEO),
    F4V("f4v", Constanst.VIDEO),
    H_264("h.264", Constanst.VIDEO),
    TXT("txt", Constanst.WORD),
    DOC("doc", Constanst.WORD),
    DOCX("docx", Constanst.WORD),
    XLS("xls", Constanst.WORD),
    XLSX("xlsx", Constanst.WORD),
    PPT("ppt", Constanst.WORD),
    PDF("pdf", Constanst.WORD),
    UNKNOW("unknow", Constanst.UNKNOW),
    ;

    @Getter
    private String type;
    @Getter
    private String msg;

    public static FileTypeEnum getFileTypeByType(String type) {
        for (FileTypeEnum fileType : FileTypeEnum.values()) {
            if (fileType.getType().equalsIgnoreCase(type)) {
                return fileType;
            }
        }
        return FileTypeEnum.UNKNOW;
    }
}