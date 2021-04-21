package com.github.lotus.com.biz.message.listener;

import com.github.lotus.com.biz.message.MessageTopic;
import in.hocg.boot.message.service.normal.redis.RedisMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2021/4/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class NoticeMessageListener extends RedisMessageListener<Object> {

    @Override
    public void onMessage(Object o) {
        log.info("===> 执行对应业务");
    }

    @Override
    protected Topic getTopic() {
        return new PatternTopic(MessageTopic.Test.getCode());
    }

}
