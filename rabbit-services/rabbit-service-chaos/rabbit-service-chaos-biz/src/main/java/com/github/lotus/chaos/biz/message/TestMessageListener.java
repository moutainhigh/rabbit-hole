package com.github.lotus.chaos.biz.message;

import com.github.lotus.chaos.biz.pojo.dto.TestMessageDto;
import in.hocg.boot.message.service.normal.redis.RedisMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2021/4/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TestMessageListener extends RedisMessageListener<TestMessageDto>
    implements InitializingBean, DisposableBean {
    private final RedisMessageListenerContainer listenerContainer;
    public static final String TOPIC = "topic#test";

    @Override
    public void onMessage(TestMessageDto message) {
        log.info("print {}", message);
    }

    @Override
    public void destroy() throws Exception {
        listenerContainer.removeMessageListener(this);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        listenerContainer.addMessageListener(this, new PatternTopic(TestMessageListener.TOPIC));
    }

}
