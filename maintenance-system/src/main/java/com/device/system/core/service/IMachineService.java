package com.device.system.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.device.system.core.entity.Machine;
import com.device.system.core.entity.criteria.MachineCriteria;
import com.device.system.core.entity.dto.MachineDTO;
import com.device.system.core.entity.vo.MachineVO;

import java.util.Map;

/**
 * <p>
 * 设备型号表 服务类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-07-03
 */
public interface IMachineService extends IService<Machine> {

    /**
     * 保存设备型号
     * @param machine
     */
    void create(MachineDTO machineDTO);

    /**
     * 查询所有设备型号
     * @param criteria
     * @param page
     */
    Map<String, Object> queryAll(MachineCriteria criteria, Page page);

    /**
     * 查询单条设备型号
     * @param id    设备id
     * @return
     */
    MachineVO queryOne(String id);
}