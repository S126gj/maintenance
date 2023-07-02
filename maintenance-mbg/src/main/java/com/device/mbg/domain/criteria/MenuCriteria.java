package com.device.mbg.domain.criteria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/28 15:00
 */
@Getter
@Setter
@Schema(description = "菜单管理入参")
public class MenuCriteria extends BaseCriteria{

    @Schema(description = "父id")
    private String pid;
}
