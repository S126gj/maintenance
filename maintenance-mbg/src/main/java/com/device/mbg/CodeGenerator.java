package com.device.mbg;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * mybatisplus 自动生成代码
 * @author GuojiShen
 * @since 2023-06-27
 */
public class CodeGenerator {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(
        "jdbc:mysql://localhost:3306/maintenance?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true",
        "root",
        "11111111"
    );

    public static void main(String[] args) {
        // 获取当前模块所在的路径
        String finalPath = String.format("%s/maintenance-system/src/main/java", System.getProperty("user.dir"));
        String xmlPath = String.format("%s/maintenance-system/src/main/java/com/device/system/core/mapper/xml", System.getProperty("user.dir"));

        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> {
                    builder.author("GuojiShen") // 设置作者
                            // .fileOverride() // 3.5.2后此方法已被弃用，无法进行全局配置，覆盖配置需要在 strategy 中设置
                            // .enableSwagger() // 开启 swagger 注解模式
                            .enableSpringdoc() // 开启 springdoc 注解模式，启用该模式后可能会出现表的 comment 无法带出情况
                            .disableOpenDir() // 禁止文件生成后弹窗文件所在路径
                            .outputDir(finalPath); // 指定输出目录
                })

                .packageConfig(builder -> {
                    builder.parent("com.device.system") // 设置父包名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            // 指定xml生成的路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, xmlPath));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_dict") // 设置需要生成的表名
                            .addTablePrefix("sys_")
                            .build()
                            // 实体类配置
                            .entityBuilder()
                            .idType(IdType.ASSIGN_ID)
                            .enableLombok()
                            .enableChainModel()
                            .enableTableFieldAnnotation()
                            .addTableFills(new Column("gmt_create", FieldFill.INSERT))
                            .addTableFills(new Column("gmt_modified", FieldFill.INSERT_UPDATE))
                            .enableFileOverride() // 文件覆盖，3.5.2后无法全局配置
                            .build()
                            // controller配置
                            .controllerBuilder()
                            .enableRestStyle()
                            .enableFileOverride() // 文件覆盖，3.5.2后无法全局配置
                            .build()
                            // service 配置
                            .serviceBuilder()
                            .enableFileOverride() // 文件覆盖，3.5.2后无法全局配置
                            .build()
                            // mapper 配置
                            .mapperBuilder()
                            .enableFileOverride() // 文件覆盖，3.5.2后无法全局配置
                            .build();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}