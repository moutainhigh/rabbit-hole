package com.github.lotus.com.biz;

import com.github.TestConstant;
import com.github.lotus.chaos.BootApplication;
import com.github.lotus.com.biz.pojo.ro.message.SendPersonalMessageRo;
import com.github.lotus.com.biz.pojo.ro.message.SendSystemMessageRo;
import com.github.lotus.com.biz.pojo.vo.message.MessageStatVo;
import com.github.lotus.com.biz.service.MessageUserRefService;
import com.github.lotus.common.constant.GlobalConstant;
import in.hocg.boot.test.autoconfiguration.core.AbstractSpringBootTest;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by hocgin on 2021/4/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@ActiveProfiles("local")
@SpringBootTest(classes = {BootApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageUserRefServiceTests extends AbstractSpringBootTest {
    @Autowired
    MessageUserRefService service;

    @Test
    public void getMessageStatByUserId() {
        MessageStatVo messageStatVo = service.getMessageStatByUserId(TestConstant.SUPPER_ADMIN_USER_ID);
        System.out.println(messageStatVo);
    }

    @Test
    public void sendPersonalMessage() {
        SendPersonalMessageRo messageRo = new SendPersonalMessageRo();
        messageRo.setUserId(TestConstant.SUPPER_ADMIN_USER_ID);
        messageRo.setContent("你好");
        messageRo.setReceiver(TestConstant.SUPPER_ADMIN_USER_ID);
        service.sendPersonalMessage(messageRo);
    }

    @Test
    public void sendSystemMessage() {
        SendSystemMessageRo messageRo = new SendSystemMessageRo();
        messageRo.setUserId(TestConstant.SUPPER_ADMIN_USER_ID);
        messageRo.setContent("你好");
        messageRo.setTitle("标题");
        messageRo.setReceiver(Lists.newArrayList(TestConstant.SUPPER_ADMIN_USER_ID));
        service.sendSystemMessage(messageRo);
    }
}
