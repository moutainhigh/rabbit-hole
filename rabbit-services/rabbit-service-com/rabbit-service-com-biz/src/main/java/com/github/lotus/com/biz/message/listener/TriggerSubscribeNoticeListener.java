package com.github.lotus.com.biz.message.listener;

import com.github.lotus.com.biz.entity.NoticeUserConfig;
import com.github.lotus.com.biz.message.MessageTopic;
import com.github.lotus.com.biz.pojo.dto.NoticeMessageDto;
import com.github.lotus.com.biz.pojo.dto.SendNoticeMessageDto;
import com.github.lotus.com.biz.service.MessageUserRefProxyService;
import com.github.lotus.com.biz.service.NoticeUserConfigService;
import in.hocg.boot.message.autoconfigure.service.normal.redis.RedisMessageListener;
import io.swagger.annotations.ApiModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hocgin on 2021/4/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@ApiModel("触发通知消息")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TriggerSubscribeNoticeListener extends RedisMessageListener<Message<NoticeMessageDto>> {
    private final NoticeUserConfigService noticeUserConfigService;
    private final MessageUserRefProxyService messageUserRefProxyService;

    @Override
    public void onMessage(Message<NoticeMessageDto> message) {
        NoticeMessageDto payload = message.getPayload();
        String eventType = payload.getEventType();
        Long refId = payload.getRefId();
        String refType = payload.getRefType();
        Long triggerUserId = payload.getTriggerUserId();

        List<NoticeUserConfig> userConfigs = noticeUserConfigService.listByEventTypeAndRefIdAndRefType(eventType, refId, refType);
        userConfigs.parallelStream().map(NoticeUserConfig::getSubscriberUser)
            .forEach(subscriberUserId -> {
                SendNoticeMessageDto noticeMessageDto = new SendNoticeMessageDto();
                noticeMessageDto.setEventType(eventType);
                noticeMessageDto.setRefType(refType);
                noticeMessageDto.setRefId(refId);
                noticeMessageDto.setCreator(triggerUserId);
                noticeMessageDto.setReceiver(subscriberUserId);
                messageUserRefProxyService.sendNoticeMessage(noticeMessageDto);
            });
    }

    @Override
    protected Topic getTopic() {
        return new PatternTopic(MessageTopic.TriggerSubscribeNotice.getCode());
    }

}
