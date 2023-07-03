package com.device.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.device.*", "com.device.system.*"})
@EnableConfigurationProperties
@EnableTransactionManagement
@ConfigurationPropertiesScan(value = {"com.device.*.config"})
public class MaintenanceSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaintenanceSystemApplication.class, args);
    }

}
