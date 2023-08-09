package com.device.forest.client;

import com.dtflys.forest.annotation.*;
import com.dtflys.forest.http.ForestResponse;
import org.springframework.stereotype.Component;

/**
 * forest参照
 * https://forest.dtflyx.com/pages/1.5.32/http_query/
 * @Author: Guoji Shen
 * @Date: 2023/6/30 13:19
 */
@BaseRequest(baseURL = "${apiUrl}") // 此处apiUrl对应配置文件中的 forest.variables.apiUrl, 其中 apiUrl可自定义名称，且可定义多个url
public interface ApiClient {

    @Get(url = "/api/test?${parameters}", contentType = "application/json")
    ForestResponse<String> test(@Var("parameters") String parameters);

    @Get(url = "/api/test2?${parameters}", contentType = "application/json")
    ForestResponse<String> test2(@Var("parameters") String parameters);
}