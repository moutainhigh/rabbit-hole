package in.hocg.rabbit.com.biz.message.listener;

import com.google.common.collect.Maps;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.rabbit.com.api.enums.integralflow.EventType;
import in.hocg.rabbit.com.api.enums.message.NoticeMessageEventType;
import in.hocg.rabbit.com.biz.entity.UserSubscriberConfig;
import in.hocg.rabbit.com.biz.message.MessageTopic;
import in.hocg.rabbit.com.biz.pojo.dto.NoticeMessageDto;
import in.hocg.rabbit.com.biz.pojo.dto.SendNoticeMessageDto;
import in.hocg.rabbit.com.biz.manager.MessageUserRefProxyService;
import in.hocg.rabbit.com.biz.service.UserSubscriberConfigService;
import in.hocg.boot.message.autoconfigure.service.normal.redis.RedisMessageListener;
import in.hocg.rabbit.com.biz.support.MessageHelper;
import io.swagger.annotations.ApiModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2021/4/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@ApiModel("订阅的通知被触发，通知订阅的相关用户")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TriggerSubscribeNoticeListener extends RedisMessageListener<Message<NoticeMessageDto>> {
    private final UserSubscriberConfigService userSubscriberConfigService;
    private final MessageUserRefProxyService messageUserRefProxyService;

    @Override
    public void onMessage(Message<NoticeMessageDto> message) {
        NoticeMessageDto payload = message.getPayload();
        String eventType = payload.getEventType();
        Long refId = payload.getRefId();
        String refType = payload.getRefType();
        Long triggerUserId = payload.getTriggerUserId();

        List<UserSubscriberConfig> userConfigs = userSubscriberConfigService.listByEventTypeAndRefIdAndRefType(eventType, refId, refType);
        List<Long> subscriberUserIds = userConfigs.stream().map(UserSubscriberConfig::getSubscriberUser).collect(Collectors.toList());
        SendNoticeMessageDto noticeMessageDto = new SendNoticeMessageDto();
        noticeMessageDto.setEventType(eventType);
        noticeMessageDto.setRefType(refType);
        noticeMessageDto.setRefId(refId);
        noticeMessageDto.setCreator(triggerUserId);
        noticeMessageDto.setReceiver(subscriberUserIds);
        noticeMessageDto.setContent(MessageHelper.getSendNoticeMessageContent(ICode.ofThrow(eventType, NoticeMessageEventType.class), Maps.newHashMap()));
        messageUserRefProxyService.sendNoticeMessage(noticeMessageDto);

        log.info("订阅的通知被触发，通知订阅的相关用户：{}", subscriberUserIds);
    }

    @Override
    protected Topic getTopic() {
        return new PatternTopic(MessageTopic.TriggerSubscribeNotice.getCode());
    }

}
