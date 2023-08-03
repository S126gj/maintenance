package com.device.system;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.device.system.core.entity.BoxRun;
import com.device.system.core.entity.Dict;
import com.device.system.core.entity.Machine;
import com.device.system.core.mapper.BoxRunMapper;
import com.device.system.core.mapper.DictMapper;
import com.device.system.core.mapper.MachineMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 分表测试
 * @Author: Guoji Shen
 * @Date: 2023/8/02 13:08
 */
@Slf4j
@SpringBootTest
class ShardTest {

    @Autowired
    private BoxRunMapper boxRunMapper;
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private MachineMapper machineMapper;

    @Test
    void insert() {
        // 此表走分表规则
        for (int i = 0; i < 1000; i++) {
            BoxRun boxRun = BoxRun.builder()
                .boxCode("123")
                .productNumberBegin(new BigDecimal(i))
                .productNumber(new BigDecimal(i))
                .avgSpeed(new BigDecimal(i).multiply(BigDecimal.TEN))
                .beginTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .duration(100L)
                .machineState(i % 2 == 0 ? "关机" : "运行")
                .build();
            boxRunMapper.insert(boxRun);
        }
    }

    @Test
    void query() {
        // 此表走分表规则
        List<BoxRun> boxRuns = boxRunMapper.selectList(null);
        log.info("boxRuns:{}", JSONUtil.toJsonStr(boxRuns));
    }

    @Test
    void dictQuery() {
        // 此表不走分表规则
        StpUtil.login("1");
        List<Dict> dicts = dictMapper.selectList(null);
        log.info("dicts:{}", JSONUtil.toJsonStr(dicts));
    }

    @Test
    void machineQuery() {
        // 此表不走分表规则
        StpUtil.login("1");
        List<Machine> machines = machineMapper.selectList(null);
        log.info("machines:{}", JSONUtil.toJsonStr(machines));
    }

}