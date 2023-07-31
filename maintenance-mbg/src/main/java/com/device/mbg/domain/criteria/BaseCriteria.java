package com.device.mbg.domain.criteria;

import cn.hutool.core.collection.CollUtil;
import com.device.common.utils.LocalDateUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/27 08:23
 */
@Getter
@Setter
@Schema(description = "总父级入参")
public class BaseCriteria implements Serializable {

    @Schema(description = "模糊搜索")
    private String blurry;

    // string 接收类型必须为 yyyy-MM-dd HH:mm:ss，否则转换出错
    @Schema(description = "日期搜索框")
    private List<String> dateRange = new ArrayList<>(2);

    public LocalDateTime getBeginTime() {
        return CollUtil.isNotEmpty(dateRange) ? LocalDateUtil.parseLocalDateTime(dateRange.get(0)).with(LocalTime.MIN) : null;
    }

    public LocalDateTime getEndTime() {
        return CollUtil.isNotEmpty(dateRange) ? LocalDateUtil.parseLocalDateTime(dateRange.get(0)).with(LocalTime.MAX) : null;
    }
}