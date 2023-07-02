package com.device.system.column.service.mapStruct;

import com.device.mbg.domain.dto.BaseMapping;
import com.device.system.column.entity.Layout;
import com.device.system.column.entity.dto.LayoutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LayoutMapping extends BaseMapping<LayoutDTO, Layout> {

}
