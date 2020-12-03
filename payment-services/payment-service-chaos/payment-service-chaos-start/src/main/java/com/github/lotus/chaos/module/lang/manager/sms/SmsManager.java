package com.github.lotus.chaos.module.lang.manager.sms;

import com.github.lotus.chaos.module.lang.manager.RedisManager;
import in.hocg.boot.sms.autoconfigure.core.SmsService;
import in.hocg.boot.web.exception.ServiceException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SmsManager {
    private final RedisManager redisManager;
    private final SmsService service;

    private void sendSms(String phone, String text) {
        log.info("发送短信, 接收人: [{}], 内容: [{}]", phone, text);
    }

    private String sendSmsVerifyCode(String phone, String code) {
        log.info("发送短信, 接收人: [{}], 内容: [{}]", phone, code);
        SmsTpl test = SmsTpl.Zhifou;
        Map<String, String> vars = new HashMap<String, String>() {{
            put("code", code);
        }};
        return service.sendSms(phone, test.getSignName(), test.getTemplateCode(), vars);
    }

    public void sendSmsCode(String phone, String code) {
        if (Boolean.TRUE.compareTo(redisManager.exitsSmsCode(phone)) == 0) {
            throw ServiceException.wrap("验证码已发送，请注意查收");
        }

        // 风控处理 24小时内发送超过5次
        redisManager.tryCheckAndAddSmsCount(phone);
        redisManager.setSmsCode(phone, code);
        this.sendSmsVerifyCode(phone, code);
    }

    public boolean validSmsCode(@NonNull String phone, @NonNull String smsCode) {
        return redisManager.validSmsCode(phone, smsCode);
    }
}
