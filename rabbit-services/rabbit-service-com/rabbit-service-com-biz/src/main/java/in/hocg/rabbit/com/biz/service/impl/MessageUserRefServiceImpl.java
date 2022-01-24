package in.hocg.rabbit.com.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.com.biz.entity.MessageUserRef;
import in.hocg.rabbit.com.biz.mapper.MessageUserRefMapper;
import in.hocg.rabbit.com.biz.mapstruct.MessageUserRefMapping;
import in.hocg.rabbit.com.biz.pojo.dto.SendPersonalMessageDto;
import in.hocg.rabbit.com.biz.pojo.dto.SendSystemMessageDto;
import in.hocg.rabbit.com.biz.pojo.ro.message.MessagePagingRo;
import in.hocg.rabbit.com.biz.pojo.ro.message.SendPersonalMessageRo;
import in.hocg.rabbit.com.biz.pojo.ro.message.SendSystemMessageRo;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageStatVo;
import in.hocg.rabbit.com.biz.service.MessageUserRefProxyService;
import in.hocg.rabbit.com.biz.service.MessageUserRefService;
import in.hocg.rabbit.com.api.enums.MessageUserRefType;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * [消息模块] 用户接收的消息表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MessageUserRefServiceImpl extends AbstractServiceImpl<MessageUserRefMapper, MessageUserRef> implements MessageUserRefService {
    private final MessageUserRefProxyService messageUserRefProxyService;
    private final UserServiceApi userServiceApi;
    private final MessageUserRefMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<MessageComplexVo> pagingWithSelf(MessagePagingRo ro) {
        return messageUserRefProxyService.paging(ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<MessageUserRef> paging(MessagePagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendPersonalMessage(SendPersonalMessageRo ro) {
        SendPersonalMessageDto dto = mapping.asSendPersonalMessageDto(ro);
        dto.setCreator(ro.getUserId());
        messageUserRefProxyService.sendPersonalMessage(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendSystemMessage(SendSystemMessageRo ro) {
        SendSystemMessageDto dto = mapping.asSendSystemMessageDto(ro);
        dto.setCreator(ro.getUserId());
        messageUserRefProxyService.sendSystemMessage(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MessageStatVo getMessageStatByUserId(Long userId) {
        MessageStatVo result = new MessageStatVo();
        if (Objects.isNull(userId)) {
            return result;
        }

        Long unreadNoticeCount = this.countByUnreadAndRefTypeAndUserId(MessageUserRefType.NoticeMessage.getCodeStr(), userId);
        Long unreadPersonalCount = this.countByUnreadAndRefTypeAndUserId(MessageUserRefType.PersonalMessage.getCodeStr(), userId);
        Long unreadSystemCount = this.countByUnreadAndRefTypeAndUserId(MessageUserRefType.SystemMessage.getCodeStr(), userId);

        result.setUnreadPersonCount(unreadPersonalCount);
        result.setUnreadNoticeCount(unreadNoticeCount);
        result.setUnreadSystemCount(unreadSystemCount);
        result.setUnreadTotalCount(unreadPersonalCount + unreadNoticeCount + unreadSystemCount);
        return result;
    }

    private Long countByUnreadAndRefTypeAndUserId(String refType, Long userId) {
        return lambdaQuery().eq(MessageUserRef::getReceiverUser, userId)
            .eq(MessageUserRef::getRefType, refType)
            .isNull(MessageUserRef::getReadAt).count();
    }

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void readById(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        lambdaUpdate().in(MessageUserRef::getId, ids).isNull(MessageUserRef::getReadAt)
            .set(MessageUserRef::getReadAt, LocalDateTime.now()).update();
    }

    @Override
    public void validEntity(MessageUserRef entity) {
        super.validEntity(entity);
        Long receiverUser = entity.getReceiverUser();
        if (Objects.nonNull(receiverUser)) {
            ValidUtils.notNull(userServiceApi.getById(receiverUser));
        }
    }
}
