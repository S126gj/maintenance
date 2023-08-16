package com.device.core.column.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.device.core.column.entity.LayoutDetails;
import com.device.core.column.mapper.LayoutDetailsMapper;
import com.device.core.column.service.LayoutDetailsService;
import com.device.core.column.entity.dto.LayoutDTO;
import com.device.core.column.entity.dto.LayoutDetailsDTO;
import com.device.core.column.service.mapStruct.LayoutDetailsMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 显示列表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-28
 */
@Service
@RequiredArgsConstructor
public class LayoutDetailsServiceImpl extends ServiceImpl<LayoutDetailsMapper, LayoutDetails> implements
    LayoutDetailsService {

    private final LayoutDetailsMapping mapping;


    /**
     * 根据pid查询
     */
    @Override
    public List<LayoutDetailsDTO> queryAllNoDocId() {
        List<LayoutDetailsDTO> layoutDetailsDTOs = mapping.toDto(baseMapper.selectList(null));
        return layoutDetailsDTOs;
    }

    /**
     * 根据pid查询
     */
    @Override
    public List<LayoutDetailsDTO> findByPid(String pid) {
        return mapping.toDto(baseMapper.findByLayoutId(pid));
    }

    /**
     * 保存
     */
    @Override
    @Transactional
    public void save(LayoutDTO dto) {
        for (LayoutDetailsDTO detailsDto : dto.getLayoutDetailsDTOs()) {
            detailsDto.setLayoutId(dto.getId());
            save(detailsDto);
        }
    }
    /**
     * 保存
     */
    @Override
    @Transactional
    public void save(LayoutDetailsDTO detailsDto) {
        String validateRules = JSONObject.toJSONString(detailsDto.getValidateRules());
        baseMapper.update(detailsDto.getId(),
            detailsDto.getLabel(),
            detailsDto.getWidth(),
            detailsDto.getVisible(),
            detailsDto.getSort(),
            detailsDto.getAuthConfig(),
            validateRules,
            detailsDto.getSortable()
        );
    }
}
