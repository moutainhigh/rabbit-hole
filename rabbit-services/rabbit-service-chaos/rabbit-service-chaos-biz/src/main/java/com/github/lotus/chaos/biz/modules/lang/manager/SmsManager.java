package com.github.lotus.chaos.biz.modules.lang.manager;

import cn.hutool.core.util.StrUtil;
import in.hocg.boot.web.exception.ServiceException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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

    private void sendSms(String phone, String text) {
        log.info("发送短信, 接收人: [{}], 内容: [{}]", phone, text);
    }

    public void sendSmsCode(String phone, String code) {
        if (Boolean.TRUE.compareTo(redisManager.exitsSmsCode(phone)) == 0) {
            throw ServiceException.wrap("验证码已发送，请注意查收");
        }
        redisManager.setSmsCode(phone, code);
        this.sendSms(phone, StrUtil.format("短信验证码: {}", code));
    }

    public boolean validSmsCode(@NonNull String phone, @NonNull String smsCode) {
        return redisManager.validSmsCode(phone, smsCode);
    }
}
