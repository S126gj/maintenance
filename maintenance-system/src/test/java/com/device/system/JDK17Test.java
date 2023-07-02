package com.device.system;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/5 10:08
 */
@SpringBootTest
public class JDK17Test {

    @Test
    public void test1() {
        var i = RandomUtil.randomInt(6);
        System.out.println(i);
        switch (i) {
            case 1 -> System.out.println("1");
            case 2 -> System.out.println("2");
            case 3 -> System.out.println("3");
            case 4 -> System.out.println("4");
            case 5 -> System.out.println("5");
            default -> System.out.println(String.format("default %s", i));
        }
    }

    @Test
    public void test2() {
        Object o = 213.3f;
        switch (o) {
            case Integer i -> System.out.println(String.format("int %s", i));
            case Long l -> System.out.println(String.format("long %s", l));
            case Double d -> System.out.println(String.format("Double %s", d));
            case String str -> System.out.println(String.format("String %s", str));
            case BigDecimal bigDecimal -> System.out.println(String.format("BigDecimal %s", bigDecimal));
            case LocalDate date -> System.out.println(String.format("LocalDate %s", date));
            default -> System.out.println(String.format("default %s", o));
        }
    }

    @Test
    public void test3() {
        var str = "{\"businessMode\":\"ALL\",\"ipmOpen\":false,\"dbName\":\"测试\",\"name\":\"test\",\"hybridOpen\":false,\"dbServer\":\"123456\"}";

        var str2 = """
            {
            "businessMode":"ALL",
            "ipmOpen":false,
            "dbName":"测试",
            "name":"test",
            "hybridOpen":false,
            "dbServer":"123456"
            }
            """;
        System.out.println(str);
        System.out.println(str2);
    }

    @Test
    public void test4() {
        var data = getData();

        Object key1 = data.get("key1");
        Object key2 = data.get("key2");

        if (key1 instanceof String str) {
            System.out.println(String.format("是字符串  %s", str));
        } else if (key1 instanceof Integer num){
            System.out.println(String.format("是数字  %s", num));
        }
        if (key2 instanceof String str) {
            System.out.println(String.format("是字符串  %s", str));
        } else if (key2 instanceof Integer num){
            System.out.println(String.format("是数字  %s", num));
        }
    }

    private static Map<String, Object> getData() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "aaa");
        data.put("key2", 111);
        return data;
    }



}
