package com.device.core;

import cn.hutool.json.JSONUtil;
import com.device.es.entity.DataAcquisition;
import com.device.es.mapper.DataAcquisitionMapper;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryChainWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/25 08:18
 */
@SpringBootTest
public class EsTest {

    @Autowired
    private DataAcquisitionMapper dataAcquisitionMapper;

    @Test
    void queryAllEsData() {
        List<DataAcquisition> dataAcquisitions = new LambdaEsQueryChainWrapper<>(dataAcquisitionMapper).orderByDesc(DataAcquisition::getCreateTime).limit(20).list();
        System.out.println(JSONUtil.toJsonStr(dataAcquisitions));
    }

}