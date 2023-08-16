package com.device.core;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.dromara.easyes.starter.config.EasyEsConfigProperties;
import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@ConfigurationPropertiesScan(value = {"com.device.*.config"})
@EsMapperScan("com.device.es.mapper") // 开启easy-es扫描
@ForestScan(basePackages = "com.device.forest")
@ComponentScan(basePackages = {"org.dromara.easyes.starter", "com.device", "com.device.core"},
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EasyEsConfigProperties.class))  // springboot3需要添加此注解过滤easy-es依赖
public class MaintenanceSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaintenanceSystemApplication.class, args);
    }

}
