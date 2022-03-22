package in.hocg.rabbit.com.biz.message.listener;

import in.hocg.rabbit.com.biz.entity.Comment;
import in.hocg.rabbit.com.biz.message.MessageTopic;
import in.hocg.rabbit.com.biz.pojo.dto.NoticeMessageDto;
import in.hocg.rabbit.com.biz.pojo.dto.SendNoticeMessageDto;
import in.hocg.rabbit.com.biz.pojo.dto.TriggerCommentedDto;
import in.hocg.rabbit.com.biz.service.CommentService;
import in.hocg.rabbit.com.biz.manager.MessageUserRefProxyService;
import in.hocg.rabbit.com.api.enums.message.NoticeMessageEventType;
import in.hocg.rabbit.com.api.enums.message.NoticeMessageRefType;
import in.hocg.boot.message.autoconfigure.service.normal.NormalMessageBervice;
import in.hocg.boot.message.autoconfigure.service.normal.redis.RedisMessageListener;
import in.hocg.rabbit.common.datadict.common.RefType;
import io.swagger.annotations.ApiModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hocgin on 2021/4/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@ApiModel("评论被回复, 通知被评论的所有人")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BeCommentedListener extends RedisMessageListener<Message<TriggerCommentedDto>> {
    private final NormalMessageBervice messageService;
    private final CommentService commentService;
    private final MessageUserRefProxyService messageUserRefProxyService;

    @Override
    public void onMessage(Message<TriggerCommentedDto> inMessage) {
        TriggerCommentedDto inPayload = inMessage.getPayload();
        LocalDateTime createdAt = inPayload.getCreatedAt();
        Long refId = inPayload.getBeCommentedId();
        Long commentId = inPayload.getCommentId();
        Long creatorId = inPayload.getCreatorId();

        Comment beCommend = commentService.getById(refId);
        Long beCommendCreator = beCommend.getCreator();
        String refType = RefType.Comment.getCodeStr();
        String eventType = NoticeMessageEventType.CommentBeEvaluated.getCodeStr();

        // 通知被评论人
        SendNoticeMessageDto messageDto = new SendNoticeMessageDto();
        messageDto.setEventType(eventType);
        messageDto.setRefId(refId);
        messageDto.setRefType(refType);
        messageDto.setCreator(creatorId);
        messageDto.setReceiver(List.of(beCommendCreator));
        messageDto.setContent("您的评论被评论");
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
