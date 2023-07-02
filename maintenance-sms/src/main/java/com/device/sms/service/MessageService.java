package com.device.sms.service;

/**
 * 短信发送service
 * @Author: Guoji Shen
 * @Date: 2022/9/20 13:29
 */
public interface MessageService {

    /**
     * 登录验证码短信模版(腾讯云)
     * @param phone         手机号
     * @param verifyCode    验证码
     * @return
     */
    boolean sendTxVerify(String phone, String verifyCode);

    /**
     * 登录验证码短信模版(容联云)
     * @param phone         手机号
     * @param verifyCode    验证码
     * @return
     */
    boolean sendRlyVerify(String phone, String verifyCode);
}