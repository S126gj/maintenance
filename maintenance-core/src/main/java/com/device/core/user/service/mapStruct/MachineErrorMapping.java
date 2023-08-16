package com.device.core.user.service.mapStruct;

import com.device.mbg.domain.dto.BaseMapping;
import com.device.core.user.entity.MachineError;
import com.device.core.user.entity.dto.MachineErrorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/3 15:24
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MachineErrorMapping extends BaseMapping<MachineErrorDTO, MachineError> {

}
