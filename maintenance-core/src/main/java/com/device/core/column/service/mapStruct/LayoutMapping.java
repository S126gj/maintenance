package com.device.core.column.service.mapStruct;

import com.device.mbg.domain.dto.BaseMapping;
import com.device.core.column.entity.Layout;
import com.device.core.column.entity.dto.LayoutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LayoutMapping extends BaseMapping<LayoutDTO, Layout> {

}
