package in.hocg.rabbit.com.biz.manager.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.com.biz.convert.MessageConvert;
import in.hocg.rabbit.com.biz.entity.MessageUserRef;
import in.hocg.rabbit.com.biz.entity.NoticeMessage;
import in.hocg.rabbit.com.biz.entity.PersonalMessage;
import in.hocg.rabbit.com.biz.entity.SystemMessage;
import in.hocg.rabbit.com.biz.manager.MessageUserRefProxyService;
import in.hocg.rabbit.com.biz.mapstruct.MessageUserRefMapping;
import in.hocg.rabbit.com.biz.mapstruct.NoticeMessageMapping;
import in.hocg.rabbit.com.biz.mapstruct.PersonalMessageMapping;
import in.hocg.rabbit.com.biz.mapstruct.SystemMessageMapping;
import in.hocg.rabbit.com.biz.pojo.dto.SendNoticeMessageDto;
import in.hocg.rabbit.com.biz.pojo.dto.SendPersonalMessageDto;
import in.hocg.rabbit.com.biz.pojo.dto.SendSystemMessageDto;
import in.hocg.rabbit.com.biz.pojo.ro.message.MessagePagingRo;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.message.NoticeMessageComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.message.PersonalMessageComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.message.SystemMessageComplexVo;
import in.hocg.rabbit.com.biz.service.*;
import in.hocg.rabbit.com.api.enums.message.MessageUserRefType;
import in.hocg.rabbit.common.utils.Rules;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
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
    private final MessageConvert convert;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MessageComplexVo getById(Long id) {
        return convert.asMessageComplexVo(messageUserRefService.getById(id));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<MessageComplexVo> paging(MessagePagingRo ro) {
        IPage<MessageUserRef> result = messageUserRefService.paging(ro);
        messageUserRefService.readById(LangUtils.toList(result.getRecords(), MessageUserRef::getId));
        return result.convert(convert::asMessageComplexVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendSystemMessage(SendSystemMessageDto dto) {
        LocalDateTime now = LocalDateTime.now();
        List<Long> receiver = dto.getReceiver();

        SystemMessage entity = systemMessageMapping.asSystemMessage(dto);
        entity.setCreatedAt(now);
        ValidUtils.isTrue(systemMessageService.validInsert(entity));

        for (Long receiverUser : receiver) {
            this.insertMessageUser(receiverUser, entity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendNoticeMessage(SendNoticeMessageDto dto) {
        LocalDateTime now = LocalDateTime.now();
        Long receiver = dto.getReceiver();

        NoticeMessage entity = noticeMessageMapping.asNoticeMessage(dto);
        entity.setCreatedAt(now);
        ValidUtils.isTrue(noticeMessageService.validInsert(entity));

        this.insertMessageUser(receiver, entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendPersonalMessage(SendPersonalMessageDto dto) {
        LocalDateTime now = LocalDateTime.now();
        Long receiver = dto.getReceiver();

        PersonalMessage entity = personalMessageMapping.asPersonalMessage(dto);
        entity.setCreatedAt(now);
        ValidUtils.isTrue(personalMessageService.validInsert(entity));

        this.insertMessageUser(receiver, entity);
    }

    private <T extends AbstractEntity<?>> void insertMessageUser(Long receiverUser, T entity) {
        MessageUserRefType messageUserRefType = (MessageUserRefType) Rules.create()
            .rule(NoticeMessage.class, Rules.Supplier(() -> MessageUserRefType.NoticeMessage))
            .rule(SystemMessage.class, Rules.Supplier(() -> MessageUserRefType.SystemMessage))
            .rule(PersonalMessage.class, Rules.Supplier(() -> MessageUserRefType.PersonalMessage))
            .of(entity.getClass()).orElseThrow(UnsupportedOperationException::new);

        Long refId = (Long) entity.pkVal();
        MessageUserRef messageUserRef = new MessageUserRef();
        messageUserRef.setRefId(refId);
        messageUserRef.setRefType(messageUserRefType.getCodeStr());
        messageUserRef.setReceiverUser(receiverUser);
        messageUserRef.setCreatedAt(LocalDateTime.now());
        boolean isOk = messageUserRefService.validInsert(messageUserRef);
        ValidUtils.isTrue(isOk);
    }

}
