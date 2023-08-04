package com.device.sms.service.impl;

import com.device.common.constanst.Constanst;
import com.device.sms.service.MessageService;
import com.device.sms.utils.RlySmsUtil;
import com.device.sms.utils.SmsConstants;
import com.device.sms.utils.TxSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 短信服务实现类
 * @Author: Guoji Shen
 * @Date: 2022/9/20 13:30
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Override
    public boolean sendTxVerify(String phone, String verifyCode) {
        return TxSmsUtil.sendMessage(phone, SmsConstants.TX_LOGIN_VERIFY_TEMPLATE_ID, new String[]{verifyCode, Constanst.DEFAULT_AUTH_CODE_EXPIRE_STR});
    }

    @Override
    public boolean sendRlyVerify(String phone, String verifyCode) {
        return RlySmsUtil.sendMessage(phone, SmsConstants.RLY_LOGIN_VERIFY_TEMPLATE_ID, new String[]{verifyCode, Constanst.DEFAULT_AUTH_CODE_EXPIRE_STR});
    }
}