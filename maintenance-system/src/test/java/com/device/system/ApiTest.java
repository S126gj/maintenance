package com.device.system;

import cn.dev33.satoken.sign.SaSignUtil;
import com.device.forest.client.ApiClient;
import com.dtflys.forest.http.ForestResponse;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * satoken签名对接，具体可查看
 * https://sa-token.cc/doc.html#/plugin/api-sign
 * @Author: Guoji Shen
 * @Date: 2023/8/9 17:12
 */
@SpringBootTest
public class ApiTest {

    @Autowired
    private ApiClient apiClient;

    @Test
    public void test() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "11");
        map.put("test", "测试1111222");
        String paramStr = SaSignUtil.addSignParamsAndJoin(map);
        ForestResponse<String> test = apiClient.test(paramStr);
        System.out.println(test.getContent());
    }

    @Test
    public void test2() {
        String paramStr = SaSignUtil.addSignParamsAndJoin(new HashMap<>());
        ForestResponse<String> test = apiClient.test2(paramStr);
        System.out.println(test.getContent());
    }
}