package com.device.system.core.service.mapStruct;

import com.device.mbg.domain.dto.BaseMapping;
import com.device.system.core.entity.Machine;
import com.device.system.core.entity.dto.MachineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/3 15:24
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MachineMapping extends BaseMapping<MachineDTO, Machine> {

}
