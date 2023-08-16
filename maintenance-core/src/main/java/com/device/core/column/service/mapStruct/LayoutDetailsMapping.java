package com.device.core.column.service.mapStruct;

import com.device.mbg.domain.dto.BaseMapping;
import com.device.core.column.entity.LayoutDetails;
import com.device.core.column.entity.dto.LayoutDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LayoutDetailsMapping extends BaseMapping<LayoutDetailsDTO, LayoutDetails> {

}