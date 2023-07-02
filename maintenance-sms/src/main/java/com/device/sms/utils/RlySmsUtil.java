package com.device.sms.utils;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

/**
 * 容联云短信发送服务
 * @Author: Guoji Shen
 * @Date: 2023/6/15 15:20
 */
@Slf4j
@Component
public class RlySmsUtil {

    // 默认模版id，测试专用
    private static final String TEMPLATE_ID = "1";
    // 成功状态码
    private static final String SUCCESS = "000000";

    /**
     *  发送短信
     * @param phone             手机号
     * @param templateId        模版id
     * @param templateParamSet  对应模版参数
     * @return
     */
    public static boolean sendMessage(String phone, String templateId, String[] templateParamSet) {
        CCPRestSmsSDK sdk = getSdk();
        HashMap<String, Object> result = sdk.sendTemplateSMS(phone, templateId, templateParamSet);
        if (SUCCESS.equals(result.get("statusCode"))) {
            // 正常返回输出data包体信息（map）
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            keySet.forEach(key -> log.info("[RlySmsUtil][sendMsg] {} = {}", key, data.get(key)));
            return true;
        } else {
            // 异常返回输出错误码和错误信息
            log.error("[RlySmsUtil][sendMsg] 错误码={}, 错误信息={}", result.get("statusCode"), result.get("statusMsg"));
            return false;
        }
    }

    public static CCPRestSmsSDK getSdk() {
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(SmsConstants.RLY_SERVER_IP, SmsConstants.RLY_SERVER_PORT);
        sdk.setAccount(SmsConstants.RLY_ACCOUNT_S_ID, SmsConstants.RLY_ACCOUNT_TOKEN);
        sdk.setAppId(SmsConstants.RLY_APP_ID);
        sdk.setBodyType(BodyType.Type_JSON);
        return sdk;
    }
}
