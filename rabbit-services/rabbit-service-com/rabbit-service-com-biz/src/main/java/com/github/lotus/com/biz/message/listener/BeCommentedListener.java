package com.github.lotus.com.biz.message.listener;

import com.github.lotus.com.biz.entity.Comment;
import com.github.lotus.com.biz.message.MessageTopic;
import com.github.lotus.com.biz.pojo.dto.NoticeMessageDto;
import com.github.lotus.com.biz.pojo.dto.SendNoticeMessageDto;
import com.github.lotus.com.biz.pojo.dto.TriggerCommentedDto;
import com.github.lotus.com.biz.service.CommentService;
import com.github.lotus.com.biz.service.MessageUserRefProxyService;
import com.github.lotus.common.datadict.com.NoticeMessageEventType;
import com.github.lotus.common.datadict.com.NoticeMessageRefType;
import in.hocg.boot.message.service.normal.NormalMessageService;
import in.hocg.boot.message.service.normal.redis.RedisMessageListener;
import io.swagger.annotations.ApiModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/4/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@ApiModel("评论被评论")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BeCommentedListener extends RedisMessageListener<TriggerCommentedDto> {
    private final NormalMessageService messageService;
    private final CommentService commentService;
    private final MessageUserRefProxyService messageUserRefProxyService;

    @Override
    public void onMessage(TriggerCommentedDto message) {
        LocalDateTime createdAt = message.getCreatedAt();
        Long refId = message.getBeCommentedId();
        Long commentId = message.getCommentId();
        Long creatorId = message.getCreatorId();

        Comment beCommend = commentService.getById(refId);
        Long beCommendCreator = beCommend.getCreator();
        String refType = NoticeMessageRefType.Comment.getCodeStr();
        String eventType = NoticeMessageEventType.CommentBeEvaluated.getName();

        // 通知被评论人
        SendNoticeMessageDto messageDto = new SendNoticeMessageDto();
        messageDto.setEventType(eventType);
        messageDto.setRefId(refId);
        messageDto.setRefType(refType);
        messageDto.setCreator(creatorId);
        messageDto.setReceiver(beCommendCreator);
        messageUserRefProxyService.sendNoticeMessage(messageDto);

        // 通知其他订阅的人
        NoticeMessageDto payload = new NoticeMessageDto()
            .setCreatedAt(createdAt).setTriggerUserId(creatorId)
            .setRefId(refId).setRefType(refType)
            .setEventType(eventType);
        messageService.asyncSend(MessageTopic.TriggerSubscribeNotice.getCode(), MessageBuilder.withPayload(payload).build());
    }

    @Override
    protected Topic getTopic() {
        return new PatternTopic(MessageTopic.TriggerCommented.getCode());
    }
}
