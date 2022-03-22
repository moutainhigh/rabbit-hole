package in.hocg.rabbit.com.biz.manager.impl;

import in.hocg.rabbit.com.biz.entity.MessageUserRef;
import in.hocg.rabbit.com.biz.entity.NoticeMessage;
import in.hocg.rabbit.com.biz.entity.PersonalMessage;
import in.hocg.rabbit.com.biz.entity.SystemMessage;
import in.hocg.rabbit.com.biz.manager.MessageUserRefProxyService;
import in.hocg.rabbit.com.biz.mapstruct.NoticeMessageMapping;
import in.hocg.rabbit.com.biz.mapstruct.PersonalMessageMapping;
import in.hocg.rabbit.com.biz.mapstruct.SystemMessageMapping;
import in.hocg.rabbit.com.biz.pojo.dto.SendNoticeMessageDto;
import in.hocg.rabbit.com.biz.pojo.dto.SendPersonalMessageDto;
import in.hocg.rabbit.com.biz.pojo.dto.SendSystemMessageDto;
import in.hocg.rabbit.com.biz.service.*;
import in.hocg.rabbit.com.biz.support.MessageHelper;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Validated
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MessageUserRefProxyServiceImpl implements MessageUserRefProxyService {
    private final NoticeMessageService noticeMessageService;
    private final NoticeMessageMapping noticeMessageMapping;
    private final PersonalMessageService personalMessageService;
    private final PersonalMessageMapping personalMessageMapping;
    private final SystemMessageService systemMessageService;
    private final SystemMessageMapping systemMessageMapping;
    private final MessageUserRefService messageUserRefService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendSystemMessage(SendSystemMessageDto dto) {
        List<Long> receiver = dto.getReceiver();

        SystemMessage entity = systemMessageMapping.asSystemMessage(dto);
        ValidUtils.isTrue(systemMessageService.validInsert(entity));

        for (Long receiverUser : receiver) {
            this.insertMessageUser(receiverUser, entity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendNoticeMessage(SendNoticeMessageDto dto) {
        List<Long> receivers = dto.getReceiver();

        NoticeMessage entity = noticeMessageMapping.asNoticeMessage(dto);
        ValidUtils.isTrue(noticeMessageService.validInsert(entity));
        receivers.forEach(receiver -> this.insertMessageUser(receiver, entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendPersonalMessage(SendPersonalMessageDto dto) {
        Long receiver = dto.getReceiver();

        PersonalMessage entity = personalMessageMapping.asPersonalMessage(dto);
        ValidUtils.isTrue(personalMessageService.validInsert(entity));

        this.insertMessageUser(receiver, entity);
    }

    private <T extends AbstractEntity<?>> void insertMessageUser(Long receiverUser, T entity) {
        MessageUserRef messageUserRef = new MessageUserRef();
        messageUserRef.setMessageId((Long) entity.pkVal());
        messageUserRef.setMessageType(MessageHelper.entityToMessageType(entity.getClass()).getCodeStr());
        messageUserRef.setReceiverUser(receiverUser);
        ValidUtils.isTrue(messageUserRefService.validInsert(messageUserRef));
    }

}
