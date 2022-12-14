package in.hocg.rabbit.com.biz;

import in.hocg.rabbit.Features;
import in.hocg.rabbit.chaos.BootApplication;
import in.hocg.rabbit.com.biz.pojo.ro.message.SendPersonalMessageRo;
import in.hocg.rabbit.com.biz.pojo.ro.message.SendSystemMessageRo;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageStatVo;
import in.hocg.rabbit.com.biz.service.MessageUserRefService;
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
        MessageStatVo messageStatVo = service.getMessageStatByUserId(Features.SUPPER_ADMIN_USER_ID);
        System.out.println(messageStatVo);
    }

    @Test
    public void sendPersonalMessage() {
        SendPersonalMessageRo messageRo = new SendPersonalMessageRo();
        messageRo.setUserId(Features.SUPPER_ADMIN_USER_ID);
        messageRo.setContent("你好");
        messageRo.setReceiver(Features.SUPPER_ADMIN_USER_ID);
        service.sendPersonalMessage(messageRo);
    }

    @Test
    public void sendSystemMessage() {
        SendSystemMessageRo messageRo = new SendSystemMessageRo();
        messageRo.setUserId(Features.SUPPER_ADMIN_USER_ID);
        messageRo.setContent("你好");
        messageRo.setTitle("标题");
        messageRo.setReceiver(Lists.newArrayList(Features.SUPPER_ADMIN_USER_ID));
        service.sendSystemMessage(messageRo);
    }
}
