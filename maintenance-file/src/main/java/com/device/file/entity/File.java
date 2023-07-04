package com.device.file.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.device.file.entity.enums.FileResouceTypeEnum;
import com.device.file.entity.enums.FileTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * 文件信息表
 * </p>
 *
 * @author GuojiShen
 * @since 2023-07-03
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_file")
@Schema(name = "File", description = "文件信息表")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "租户id")
    @TableField("tenant_id")
    private String tenantId;

    @NotBlank(message = "来源id不能为空")
    @Schema(description = "来源id")
    @TableField("resource_id")
    private String resourceId;

    @Schema(description = "来源单号")
    @TableField("resource_code")
    private String resourceCode;

    @Schema(description = "来源明细id")
    @TableField("resource_details_id")
    private String resourceDetailsId;

    @NotBlank(message = "来源类型不能为空")
    @Schema(description = "来源类型")
    @TableField("resource_type")
    private FileResouceTypeEnum resourceType;

    @Schema(description = "文件类型")
    @TableField("fileType")
    private String fileType;

    @Schema(description = "文件类型")
    @TableField("type")
    private FileTypeEnum type;

    @Schema(description = "文件类型")
    @TableField(value = "typeDesc", exist = false)
    private String typeDesc;

    @NotBlank(message = "文件名称不能为空")
    @Schema(description = "文件名称")
    @TableField("name")
    private String name;

    @Schema(description = "文件大小(M)")
    @TableField("size")
    private Double size;

    @Schema(description = "上传人")
    @TableField("upload_man")
    private String uploadMan;

    @Schema(description = "文件路径")
    @TableField("path")
    private String path;

    @Schema(description = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime gmtCreate;

    @Schema(description = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime gmtModified;

    public String getTypeDesc() {
        return Optional.ofNullable(type).map(FileTypeEnum::getMsg).orElse(null);
    }
}
