package com.device.mbg.domain.criteria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/28 15:15
 */
@Getter
@Setter
@Schema(description = "角色管理入参")
public class RoleCriteria extends BaseCriteria{

    @Schema(description = "是否启用")
    private boolean enable = true;
}
