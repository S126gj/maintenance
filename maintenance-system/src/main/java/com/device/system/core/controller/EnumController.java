package com.device.system.core.controller;

import cn.hutool.core.util.EnumUtil;
import com.device.common.utils.R;
import com.device.file.entity.enums.FileResouceTypeEnum;
import com.device.system.core.entity.enums.DictEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/4 15:36
 */
@RestController
@RequestMapping("/enum")
@Tag(name = "枚举")
public class EnumController {

    @Operation(summary = "字典类型")
    @GetMapping("/dictType")
    public R dictType(){
        return R.ok().data(EnumUtil.getNameFieldMap(DictEnum.class, "msg"));
    }

    @Operation(summary = "文件来源类型")
    @GetMapping("/fileResource")
    public R fileResource(){
        return R.ok().data(EnumUtil.getNameFieldMap(FileResouceTypeEnum.class, "msg"));
    }
}