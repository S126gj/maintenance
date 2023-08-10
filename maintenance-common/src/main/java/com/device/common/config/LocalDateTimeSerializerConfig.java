package com.device.common.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 全局处理 LocalDate、LocalDateTime、LocalTime 格式
 * 如：2023-06-28T09:56:44 转换为 2023-06-28 09:56:44
 * @Author: Guoji Shen
 * @Date: 2023/6/30 13:30
 */
@Configuration
public class LocalDateTimeSerializerConfig {

    private static final String LOCAL_DATE_PATTERN = "yyyy-MM-dd";
    private static final String LOCAL_TIME_PATTERN = "HH:mm:ss";
    private static final String LOCAL_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";


    @Bean
    public LocalDateSerializer localDateSerializer() {
        return new LocalDateSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN));
    }

    @Bean
    public LocalDateDeserializer localDateDeserializer() {
        return new LocalDateDeserializer(DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN));
    }

    @Bean
    public LocalTimeSerializer localTimeSerializer() {
        return new LocalTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_TIME_PATTERN));
    }

    @Bean
    public LocalTimeDeserializer localTimeDeserializer() {
        return new LocalTimeDeserializer(DateTimeFormatter.ofPattern(LOCAL_TIME_PATTERN));
    }

    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_DATETIME_PATTERN));
    }

    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(LOCAL_DATETIME_PATTERN));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDate.class, localDateSerializer())
            .serializerByType(LocalTime.class, localTimeSerializer())
            .serializerByType(LocalDateTime.class, localDateTimeSerializer())
            .deserializerByType(LocalDate.class, localDateDeserializer())
            .deserializerByType(LocalTime.class, localTimeDeserializer())
            .deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
    }
}
