package com.device.sms.utils;

import cn.hutool.json.JSONUtil;
import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: Guoji Shen
 * @Date: 2023/8/7 13:26
 */
@Slf4j
@Component
public class AliSmsUtil {

    public static boolean sendMessage(String phone, String templateId, Map<String, Object> map) throws ExecutionException, InterruptedException {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
            .accessKeyId(SmsConstants.ALI_ACCESS_KEY_ID)
            .accessKeySecret(SmsConstants.ALI_ACCESS_KEY_SECRET)
            .build());

        AsyncClient client = AsyncClient.builder()
            .credentialsProvider(provider)
            .overrideConfiguration(
                ClientOverrideConfiguration.create()
                    .setEndpointOverride(SmsConstants.ALI_ENDPOINT)
            )
            .build();

        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
            .phoneNumbers(phone)
            .signName(SmsConstants.ALI_SIGN_NAME)
            .templateCode(templateId)
            .templateParam(JSONUtil.toJsonStr(map))
            .build();

        try {
            CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
            SendSmsResponse resp = response.get();
            log.info("[AliSmsUtil][sendMessage] res:{}", new Gson().toJson(resp));
            String code = resp.getBody().getCode();
            return "OK".equals(code) ? true : false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            client.close();
        }
    }
}