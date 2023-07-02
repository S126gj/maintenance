package com.device.system.api.client;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.http.ForestResponse;
import org.springframework.stereotype.Component;

/**
 * forest参照    https://blog.csdn.net/weixin_41133233/article/details/113355741
 * @Author: Guoji Shen
 * @Date: 2023/6/30 13:19
 */
@Component
@BaseRequest(
    baseURL = "${apiUrl}",        // 默认域名
    sslProtocol = "sslType"                 // 默认单向SSL协议(TLS)
)
public interface ApiClient {

    @Post(url = "/send", contentType = "application/json")
    ForestResponse<String> send(@Body Object obj);
}