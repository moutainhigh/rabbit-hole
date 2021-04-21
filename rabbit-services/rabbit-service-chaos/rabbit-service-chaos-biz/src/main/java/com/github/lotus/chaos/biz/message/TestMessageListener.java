package com.github.lotus.chaos.biz.message;

import com.github.lotus.chaos.biz.pojo.dto.TestMessageDto;
import in.hocg.boot.message.service.normal.redis.RedisMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.messaging.Message;
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
public class TestMessageListener extends RedisMessageListener<Message<TestMessageDto>> {
    public static final String TOPIC = "topic#test";

    @Override
    public void onMessage(Message<TestMessageDto> message) {
        log.info("print {}", message);
    }

    @Override
    protected Topic getTopic() {
        return new PatternTopic(TOPIC);
    }

}
