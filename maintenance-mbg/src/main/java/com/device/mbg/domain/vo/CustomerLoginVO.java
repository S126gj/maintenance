package com.device.mbg.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/28 11:03
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "客户登录返回视图")
public class CustomerLoginVO implements Serializable {

    @Schema(description = "token")
    private String token;
}