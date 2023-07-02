package com.device.system.column.entity;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 显示列表
 * </p>
 *
 * @author ${author}
 * @since 2020-07-28
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_layout_d")
@Schema(name = "Layout_d对象", description = "显示明细列表")
public class LayoutDetails extends Model<LayoutDetails> {

    private static final long serialVersionUID = 1L;

    public static final String LAYOUT_D_SESSION_KEY = StpUtil.getLoginId() + "_" + "LAYOUT_D_SESSION_KEY";


    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(name = "单据id")
    private String layoutId;

    @Schema(name = "租户id")
    private String tenantId;

    @Schema(name = "序号")
    private Integer sort;

    @Schema(name = "字段名称")
    private String fieldName;

    @Schema(name = "显示名称")
    private String label;

    @Schema(name = "显示宽度")
    private Integer width;

    @Schema(name = "显示类型")
    private String inputType;// text input dialog select filter date time button money dateRange timeRange

    @Schema(name = "是否显示")
    private Boolean visible;

    @Schema(name = "是否只读")
    private Boolean readonly;

    @Schema(name = "表单类型")
    private String layoutType;

    @Schema(name = "数据源")
    private String dataSource;

    @Schema(name = "表单验证")
    private List<Object> validateRules;

    @Schema(name = "下拉搜索")
    private Boolean filterable;

    @Schema(name = "用户角色表ID")
    private String authConfig;

    @Schema(name = "前端排序")
    private Boolean sortable;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
