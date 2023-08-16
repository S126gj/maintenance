package com.device.core.column.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 显示列表
 * </p>
 */
@Getter
@Setter
public class LayoutDetailsDTO implements Serializable, Comparator {

    private static final long serialVersionUID = 1L;

    @Schema(name = "id")
    private String id;

    @Schema(name = "单据id")
    private String layoutId;

    @Schema(name = "序号")
    private Integer sort;

    @Schema(name = "字段名称")
    private String fieldName;

    @Schema(name = "显示名称")
    private String label;

    @Schema(name = "显示宽度")
    private Integer width;

    @Schema(name = "显示类型")
    private String inputType;

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
    public int compare(Object o1, Object o2) {
        LayoutDetailsDTO l1 = (LayoutDetailsDTO) o1;
        LayoutDetailsDTO l2 = (LayoutDetailsDTO) o2;
        return l1.getSort().compareTo(l2.getSort());
    }

}
