package com.device.sms.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * sms 短信配置
 * @Author: Guoji Shen
 * @Date: 2023/6/26 13:29
 */
@Component
public class SmsConstants {

    /*------------------------------阿里云------------------------------*/
    @Value("${sms.ali.accessKeyId}")
    private String ALI_SMS_ACCESS_KEY_ID;
    public static String ALI_ACCESS_KEY_ID;
    @Value("${sms.ali.accessKeySecret}")
    private String ALI_SMS_ACCESS_KEY_SECRET;
    public static String ALI_ACCESS_KEY_SECRET;
    @Value("${sms.ali.endpoint}")
    private String ALI_SMS_ENDPOINT;
    public static String ALI_ENDPOINT;
    @Value("${sms.ali.signName}")
    private String ALI_SMS_SIGN_NAME;
    public static String ALI_SIGN_NAME;
    @Value("${sms.ali.loginVerifyTemplateId}")
    private String ALI_SMS_LOGIN_VERIFY_TEMPLATEID;
    public static String ALI_SIGN_LOGIN_VERIFY_TEMPLATEID;

    /*------------------------------腾讯云------------------------------*/
    /**
     * SecretId、SecretKey 查询: https://console.cloud.tencent.com/cam/capi
     */
    @Value("${sms.tx.secretId}")
    private String TX_SMS_SECRET_ID;
    public static String TX_SECRET_ID;
    @Value("${sms.tx.secretKey}")
    private String TX_SMS_SECRET_KEY;
    public static String TX_SECRET_KEY;

    /**
     * 指定接入地域域名，默认就近地域接入域名为 sms.tencentcloudapi.com ，也支持指定地域域名访问，例如广州地域的域名为 sms.ap-guangzhou.tencentcloudapi.com
     */
    @Value("${sms.tx.endpoint}")
    private String TX_SMS_ENDPOINT;
    public static String TX_ENDPOINT;
    @Value("${sms.tx.region}")
    private String TX_SMS_REGION;
    public static String TX_REGION;

    /**
     * 应用 ID 可前往 [短信控制台](https://console.cloud.tencent.com/smsv2/app-manage) 查看
     */
    @Value("${sms.tx.sdkAppId}")
    private String TX_SMS_SDK_APP_ID;
    public static String TX_SDK_APP_ID;

    /**
     * 签名信息可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-sign) 或 [国际/港澳台短信](https://console.cloud.tencent.com/smsv2/isms-sign) 的签名管理查看
     */
    @Value("${sms.tx.signName}")
    private String TX_SMS_SIGN_NAME;
    public static String TX_SIGN_NAME;

    /**
     * 登录验证码短信模版
     */
    @Value("${sms.tx.loginVerifyTemplateId}")
    private String TX_SMS_LOGIN_VERIFY_TEMPLATE_ID;
    public static String TX_LOGIN_VERIFY_TEMPLATE_ID;

    /*------------------------------容联云------------------------------*/
    @Value("${sms.rly.serverIp}")
    private String RLY_SMS_SERVER_IP;
    public static String RLY_SERVER_IP;

    @Value("${sms.rly.serverPort}")
    private String RLY_SMS_SERVER_PORT;
    public static String RLY_SERVER_PORT;

    @Value("${sms.rly.accountSId}")
    private String RLY_SMS_ACCOUNT_S_ID;
    public static String RLY_ACCOUNT_S_ID;

    @Value("${sms.rly.accountToken}")
    private String RLY_SMS_ACCOUNT_TOKEN;
    public static String RLY_ACCOUNT_TOKEN;

    @Value("${sms.rly.appId}")
    private String RLY_SMS_APP_ID;
    public static String RLY_APP_ID;

    @Value("${sms.rly.loginVerifyTemplateId}")
    private String RLY_SMS_LOGIN_VERIFY_TEMPLATE_ID;
    public static String RLY_LOGIN_VERIFY_TEMPLATE_ID;


    /**
     * 初始化
     */
    @PostConstruct
    private void init() {
        ALI_ACCESS_KEY_ID = ALI_SMS_ACCESS_KEY_ID;
        ALI_ACCESS_KEY_SECRET = ALI_SMS_ACCESS_KEY_SECRET;
        ALI_ENDPOINT = ALI_SMS_ENDPOINT;
        ALI_SIGN_NAME = ALI_SMS_SIGN_NAME;
        ALI_SIGN_LOGIN_VERIFY_TEMPLATEID = ALI_SMS_LOGIN_VERIFY_TEMPLATEID;
        TX_SECRET_ID = TX_SMS_SECRET_ID;
        TX_SECRET_KEY = TX_SMS_SECRET_KEY;
        TX_REGION = TX_SMS_REGION;
        TX_SDK_APP_ID = TX_SMS_SDK_APP_ID;
        TX_SIGN_NAME = TX_SMS_SIGN_NAME;
        TX_LOGIN_VERIFY_TEMPLATE_ID = TX_SMS_LOGIN_VERIFY_TEMPLATE_ID;
        RLY_SERVER_IP = RLY_SMS_SERVER_IP;
        RLY_SERVER_PORT = RLY_SMS_SERVER_PORT;
        RLY_ACCOUNT_S_ID = RLY_SMS_ACCOUNT_S_ID;
        RLY_ACCOUNT_TOKEN = RLY_SMS_ACCOUNT_TOKEN;
        RLY_APP_ID = RLY_SMS_APP_ID;
        RLY_LOGIN_VERIFY_TEMPLATE_ID = RLY_SMS_LOGIN_VERIFY_TEMPLATE_ID;
    }
}