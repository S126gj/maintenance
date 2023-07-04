package com.device.system.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.device.common.utils.StringUtil;
import com.device.system.core.entity.MachineError;
import com.device.system.core.entity.dto.MachineErrorDTO;
import com.device.system.core.mapper.MachineErrorMapper;
import com.device.system.core.service.IMachineErrorService;
import com.device.system.core.service.mapStruct.MachineErrorMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 设备故障表 服务实现类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-07-03
 */
@Service
@RequiredArgsConstructor
public class MachineErrorServiceImpl extends ServiceImpl<MachineErrorMapper, MachineError> implements IMachineErrorService {

    private final MachineErrorMapping machineErrorMapping;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(String machineId, List<MachineErrorDTO> machineErrorDTOS) {
        if (CollUtil.isNotEmpty(machineErrorDTOS)) {
            List<MachineError> newData = machineErrorMapping.toEntity(machineErrorDTOS);
            newData.forEach(item -> item.setMachineId(machineId));
            List<MachineError> oldData = new LambdaQueryChainWrapper<>(baseMapper).eq(MachineError::getMachineId,
                machineId).list();

            List<MachineError> saveOrUpdateData = new ArrayList<>();
            List<MachineError> deleteData = new ArrayList<>();

            if (CollUtil.isEmpty(oldData)) {
                saveOrUpdateData.addAll(newData);
            } else {
                oldData.forEach(old -> {
                    newData.forEach(n -> {
                        if (old.getId().equals(n.getId()) || StringUtil.isBlank(n.getId())) {
                            saveOrUpdateData.add(n);
                        } else {
                            deleteData.add(old);
                        }
                    });
                });
            }
            this.saveOrUpdateBatch(saveOrUpdateData);
            this.removeBatchByIds(deleteData.stream().map(MachineError::getId).collect(Collectors.toList()));
        }
    }

    @Override
    public List<MachineError> findByMachineId(String machineId) {
        return new LambdaQueryChainWrapper<>(baseMapper).eq(MachineError::getMachineId, machineId).list();
    }
}
