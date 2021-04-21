package com.github.lotus.com.biz.message.listener;

import com.github.lotus.com.biz.message.MessageTopic;
import com.github.lotus.com.biz.pojo.dto.NoticeMessageDto;
import com.github.lotus.com.biz.pojo.dto.TriggerCommentedDto;
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
@ApiModel("评论后触发")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CommentedListener extends RedisMessageListener<TriggerCommentedDto> {
    private final NormalMessageService messageService;

    @Override
    public void onMessage(TriggerCommentedDto message) {
        LocalDateTime createdAt = message.getCreatedAt();
        Long commentId = message.getCommentId();
        Long creatorId = message.getCreatorId();

        // 被评论通知
        NoticeMessageDto payload = new NoticeMessageDto()
            .setCreatedAt(createdAt).setRefId(commentId)
            .setRefType(NoticeMessageRefType.Comment.getCodeStr()).setTriggerUserId(creatorId)
            .setEventType(NoticeMessageEventType.CommentBeEvaluated.getName());
        messageService.asyncSend(MessageTopic.TriggerSubscribeNotice.getCode(), MessageBuilder.withPayload(payload).build());
    }

    @Override
    protected Topic getTopic() {
        return new PatternTopic(MessageTopic.TriggerCommented.getCode());
    }
}
