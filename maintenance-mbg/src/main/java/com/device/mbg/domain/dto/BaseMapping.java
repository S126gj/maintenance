package com.device.mbg.domain.dto;

import org.mapstruct.MapperConfig;

import java.util.List;
import java.util.Set;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/29 08:48
 */
@MapperConfig
public interface BaseMapping<D, E> {

    /**
     * DTO转Entity
     * @param dto /
     * @return /
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     * @param entity /
     * @return /
     */
    D toDto(E entity);

    /**
     * DTO集合转Entity集合
     * @param dtoList /
     * @return /
     */
    List<E> toEntity(List<D> dtoList);

    Set<E> toEntity(Set<D> dtoList);

    /**
     * Entity集合转DTO集合
     * @param entityList /
     * @return /
     */
    List<D> toDto(List<E> entityList);

    Set<D> toDto(Set<E> entityList);
}
