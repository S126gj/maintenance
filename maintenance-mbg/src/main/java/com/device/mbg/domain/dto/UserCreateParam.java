package com.device.mbg.domain.dto;

import com.device.mbg.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/30 09:38
 */
@Getter
@Setter
@Schema(description = "用户创建传参")
public class UserCreateParam extends User {

    @Schema(description = "角色")
    private List<String> roleList;

}
