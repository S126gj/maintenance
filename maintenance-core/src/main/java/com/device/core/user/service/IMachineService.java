package com.device.core.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.device.core.user.entity.Machine;
import com.device.core.user.entity.criteria.MachineCriteria;
import com.device.core.user.entity.dto.MachineDTO;
import com.device.core.user.entity.vo.MachineVO;

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