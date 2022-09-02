package in.hocg.rabbit.chaos.biz.manager;

import com.google.common.collect.Maps;
import in.hocg.boot.sms.autoconfigure.core.SmsBervice;
import in.hocg.boot.web.autoconfiguration.properties.BootProperties;
import in.hocg.rabbit.chaos.biz.enums.SmsTpl;
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

    public void sendVerifyCode(String phone, String code) {
        this.sendSmsVerifyCode(phone, code);
    }
}
