package com.device.system.column.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
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
public class LayoutDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(name = "ID")
    private String id;

    @Schema(name = "单据名称")
    private String name;

    @Schema(name = "显示列明细")
    private List<LayoutDetailsDTO> layoutDetailsDTOs = new ArrayList<>();

}
