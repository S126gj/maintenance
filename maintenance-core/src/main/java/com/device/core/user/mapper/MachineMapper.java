package com.device.core.user.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.device.core.user.entity.Machine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.device.core.user.entity.criteria.MachineCriteria;
import com.device.core.user.entity.vo.MachineVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 设备型号表 Mapper 接口
 * </p>
 *
 * @author GuojiShen
 * @since 2023-07-03
 */
public interface MachineMapper extends BaseMapper<Machine> {

    /**
     * 查询全部设备型号
     * @param criteria
     * @param page
     * @return
     */
    IPage<MachineVO> queryAll(@Param("criteria") MachineCriteria criteria, Page page);
}
