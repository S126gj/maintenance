package com.device.system.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.device.common.utils.R;
import com.device.system.core.entity.Dict;
import com.device.system.core.entity.enums.DictEnum;
import com.device.system.core.service.IDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-30
 */
@RestController
@RequestMapping("/dict")
@Tag(name = "数据字典")
public class DictController {

    @Autowired
    private IDictService dictService;

    @Operation(summary = "获取字典类型")
    @GetMapping("/getDictByType")
    public R getDictType(){
        return R.ok().data(dictService.getDictByType());
    }

    @Operation(summary = "获取对应字典启用数据")
    @GetMapping(value = "/queryAll")
    public R queryAll(@Parameter(description = "字典类型") @RequestParam(value = "type") DictEnum dict,
                      @Parameter(description = "是否启用") @RequestParam(value = "enable") boolean enable,
                      Page page) {
        if (Objects.isNull(dict)) {
            return R.ok();
        }
        return R.ok().data(dictService.queryAll(dict, enable, page));
    }

    @Operation(summary = "新增字典")
    @PostMapping("/create")
    public R save(@Validated @RequestBody Dict dict){
        dictService.create(dict);
        return R.ok();
    }

    @Operation(summary = "修改字典状态")
    @PostMapping(value = "/updateStatus")
    public R updateStatus(@Parameter(description = "字典id") @RequestParam(value = "id") String id,
        @Parameter(description = "0->禁用；1->启用") @RequestParam(value = "status") Integer status) {
        dictService.updateStatus(id, status);
        return R.ok();
    }
}
