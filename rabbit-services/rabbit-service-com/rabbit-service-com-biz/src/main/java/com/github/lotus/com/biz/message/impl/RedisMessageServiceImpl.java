package com.github.lotus.com.biz.message.impl;

import com.github.lotus.com.biz.message.MessageService;
import com.github.lotus.com.biz.message.MessageTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2021/4/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RedisMessageServiceImpl implements MessageService {
    private final RedisTemplate redisTemplate;

    @Override
    public boolean syncSend(MessageTopic topic, Object message) {
        redisTemplate.convertAndSend(topic.getCode(), message);
        return true;
    }
}
