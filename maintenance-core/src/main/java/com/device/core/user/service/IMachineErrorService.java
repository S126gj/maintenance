package com.device.core.user.service;

import com.device.core.user.entity.MachineError;
import com.baomidou.mybatisplus.extension.service.IService;
import com.device.core.user.entity.dto.MachineErrorDTO;

import java.util.List;

/**
 * <p>
 * 设备故障表 服务类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-07-03
 */
public interface IMachineErrorService extends IService<MachineError> {

    /**
     * 保存故障信息
     * @param machineErrorDTOS
     */
    void create(String id, List<MachineErrorDTO> machineErrorDTOS);

    /**
     * 根据设备型号id查询故障信息
     * @param machineId
     * @return
     */
    List<MachineError> findByMachineId(String machineId);
}
