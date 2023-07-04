package com.device.mbg.domain.criteria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/28 14:46
 */
@Getter
@Setter
@Schema(description = "用户管理入参")
public class UserCriteria extends BaseCriteria{

    @Schema(description = "是否启用")
    private boolean enable = true;
}