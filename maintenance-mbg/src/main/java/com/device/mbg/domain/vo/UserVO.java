package com.device.mbg.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.device.mbg.domain.entity.User;
import com.device.mbg.handler.ListConvertHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/30 09:15
 */
@Getter
@Setter
@Schema(description = "用户视图")
public class UserVO extends User {

    @Schema(description = "启用")
    private String statusDesc;

    @Schema(description = "性别")
    private String genderDesc;

    public String getStatusDesc() {
        return getStatus().equals(1) ? "启用" : "禁用";
    }

    public String getGenderDesc() {
        String desc = null;
        switch (getGender()) {
            case 1 -> desc = "男";
            case 2 -> desc = "女";
            default -> desc = "未知";
        }
        return desc;
    }
}