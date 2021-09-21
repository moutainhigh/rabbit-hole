package com.github.lotus;

import com.github.lotus.chaos.BootApplication;
import com.github.lotus.chaos.biz.message.TestMessageListener;
import com.github.lotus.chaos.biz.pojo.dto.TestMessageDto;
import in.hocg.boot.test.autoconfiguration.core.AbstractSpringBootTest;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by hocgin on 2021/4/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@ActiveProfiles("local")
@SpringBootTest(classes = {BootApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisMessageTests extends AbstractSpringBootTest {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void test() {
        TestMessageDto message = new TestMessageDto();
        message.setBody("Hi");
        redisTemplate.convertAndSend(TestMessageListener.TOPIC, message);
    }
}
