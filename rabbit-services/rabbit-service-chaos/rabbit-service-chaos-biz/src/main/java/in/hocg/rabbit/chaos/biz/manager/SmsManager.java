package in.hocg.rabbit.chaos.biz.manager;

import in.hocg.rabbit.chaos.biz.manager.sms.SmsTpl;
import com.google.common.collect.Maps;
import in.hocg.boot.sms.autoconfigure.core.SmsBervice;
import in.hocg.boot.web.autoconfiguration.properties.BootProperties;
import in.hocg.boot.utils.exception.ServiceException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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
    private final SmsBervice smsBervice;
    private final BootProperties bootProperties;

    private void sendSmsVerifyCode(String phone, String text) {
        log.info("发送短信, 接收人: [{}], 内容: [{}]", phone, text);
        if (bootProperties.getIsDebug()) {
            return;
        }

        SmsTpl tpl = SmsTpl.VerifyCode;
        Map<String, String> vars = Maps.newHashMap();
        vars.put("code", text);
        smsBervice.sendSms(phone, tpl.getSignName(), tpl.getTemplateCode(), vars);
    }

    public Long sendVerifyCode(String phone, String code) {
        if (Boolean.TRUE.compareTo(redisManager.exitsVerifyCodeByPhone(phone)) == 0) {
            throw ServiceException.wrap("验证码已发送，请注意查收");
        }
        this.sendSmsVerifyCode(phone, code);
        return redisManager.setVerifyCodeByPhone(phone, code);
    }

    public boolean validVerifyCode(@NonNull String phone, @NonNull String smsCode) {
        return redisManager.validVerifyCodeByPhone(phone, smsCode);
    }
}
