package com.device.common.config;

import cn.hutool.json.JSONNull;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * 解决hutool解析json转换null值序列化报错
 * @Author: Guoji Shen
 * @Date: 2023/8/14 16:20
 */
@JsonComponent
public class JsonNullSerizlizer extends JsonSerializer<JSONNull> {

    @Override
    public void serialize(JSONNull jsonNull, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException {
        jsonGenerator.writeNull();
    }
}