package in.hocg.rabbit.chaos.biz.manager;

import cn.hutool.core.util.StrUtil;
import in.hocg.boot.mail.autoconfigure.core.MailBervice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2021/1/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class EmailManager {
    private final MailBervice service;

    private void sendEmail(String email, String text) {
        log.info("发送邮件, 接收人: [{}], 内容: [{}]", email, text);
        service.sendText(email, "验证码", text);
    }

    public void sendVerifyCode(String email, String code) {
        this.sendEmail(email, StrUtil.format("验证码: {}", code));
    }
}
