package com.device.mbg.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/28 17:33
 */
@Getter
@Setter
@Schema(description = "用户登录参数")
public class UserLoginParam {

    @NotBlank(message = "租户不能为空")
    @Schema(description = "租户")
    private String tenant;

    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;

}
