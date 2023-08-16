package com.device.core.column.entity.dto;

import com.device.mbg.domain.criteria.BaseCriteria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class LayoutDetailsQueryCriteria extends BaseCriteria {

    @Schema(name = "单据名称")
    private String name;

    @Schema(name = "业务类别")
    private String businessType;

    @Schema(name = "序号")
    private Integer sort;

    @Schema(name = "字段名称")
    private String fieldName;

    @Schema(name = "显示名称")
    private String label;

    @Schema(name = "显示宽度")
    private String width;

    @Schema(name = "显示类型")
    private String inputType;// text input dialog select filter date time button money

    @Schema(name = "是否显示")
    private Boolean visible;

    @Schema(name = "是否只读")
    private Boolean readonly;

    @Schema(name = "表单类型")
    private String layoutType;// table form

    @Schema(name = "数据源")
    private String dataSource;

    @Schema(name = "表单验证")
    private String validateRules;

    @Schema(name = "制单开始日期")
    private List<String> BeginDate;

    @Schema(name = "制单结束日期")
    private Date EndDate;

}
