package com.device.core.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.device.common.exception.Checker;
import com.device.common.exception.Errors;
import com.device.file.entity.File;
import com.device.file.entity.enums.FileResouceTypeEnum;
import com.device.file.service.IFileService;
import com.device.mbg.utils.PageUtil;
import com.device.core.user.entity.Machine;
import com.device.core.user.entity.criteria.MachineCriteria;
import com.device.core.user.entity.dto.MachineDTO;
import com.device.core.user.entity.vo.MachineVO;
import com.device.core.user.mapper.MachineMapper;
import com.device.core.user.service.IMachineErrorService;
import com.device.core.user.service.IMachineService;
import com.device.core.user.service.mapStruct.MachineMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备型号表 服务实现类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-07-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MachineServiceImpl extends ServiceImpl<MachineMapper, Machine> implements IMachineService {

    private final MachineMapping machineMapping;
    private final IMachineErrorService machineErrorService;
    private final IFileService fileService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(MachineDTO machineDTO) {
        Machine machine = machineMapping.toEntity(machineDTO);
        this.saveOrUpdate(machine);
        // 保存故障信息
        machineErrorService.create(machine.getId(), machineDTO.getMachineErrorDTOS());
        // 保存附件
        Checker.ifNotEmptyThen(machineDTO.getFiles(), then -> bindAttachment(machine.getId(), then));
    }

    @Override
    public Map<String, Object> queryAll(MachineCriteria criteria, Page page) {
        IPage<MachineVO> iPage = baseMapper.queryAll(criteria, page);
        return PageUtil.toPage(iPage);
    }

    @Override
    public MachineVO queryOne(String id) {
        Machine machine = baseMapper.selectById(id);
        Checker.ifNullThrow(machine, () -> Errors.BIZ.exception("未查询到该设备型号!"));

        MachineVO vo = new MachineVO();
        BeanUtil.copyProperties(machine, vo);

        // 查询对应附件
        List<File> fileList = new LambdaQueryChainWrapper<>(fileService.getBaseMapper()).eq(File::getResourceId, id).list();
        Checker.ifNotEmptyThen(fileList, then -> vo.setFiles(fileList));

        // 查询对应故障信息
        vo.setMachineErrors(machineErrorService.findByMachineId(id));
        log.info("[MachineServiceImpl][queryOne] vo:{}", JSONUtil.toJsonStr(vo));
        return vo;
    }

    /**
     * 保存附件
     * @param machineId
     * @param files
     * @return
     */
    public void bindAttachment(String machineId, List<File> files) {
        files.forEach(file -> {
            file.setResourceId(machineId);
            file.setResourceType(FileResouceTypeEnum.MACHINE);
        });
        fileService.saveOrUpdateBatch(files);
    }
}
