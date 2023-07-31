package com.device.system;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.device.common.utils.ExcelUtil;
import com.device.system.core.entity.Machine;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/31 13:08
 */
@SpringBootTest
@Slf4j
public class EasyexcelTest {

    @Test
    public void write() {
        String path = "/Users/shenguoji/Downloads";
        String fileName = String.format("%s %s.xlsx", "设备", LocalDateTime.now().toLocalDate().toString());
        ExcelUtil.download(path, fileName, data(), Machine.class);
    }

    @Test
    public void read() {
        String path = "/Users/shenguoji/Downloads";
        String fileName = "test.xlsx";
        List<Machine> read = ExcelUtil.read(path, fileName, Machine.class);
        System.out.println(JSONUtil.toJsonStr(read));
    }

    private List<Machine> data() {
        List<Machine> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            Machine data = new Machine();
            data.setName("网点 " + i);
            data.setType("AW2");
            data.setWeight(2);
            data.setRatedVoltage(220);
            data.setRemark("1231241sadfafqw41241241");
            data.setGmtCreate(LocalDateTime.now());
            list.add(data);
        }
        return list;
    }
}