package in.hocg.rabbit.com.biz.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.utils.PageUtils;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.com.api.enums.message.MessageType;
import in.hocg.rabbit.com.biz.convert.MessageConvert;
import in.hocg.rabbit.com.biz.entity.MessageUserRef;
import in.hocg.rabbit.com.biz.mapper.MessageUserRefMapper;
import in.hocg.rabbit.com.biz.mapstruct.MessageUserRefMapping;
import in.hocg.rabbit.com.biz.pojo.dto.SendPersonalMessageDto;
import in.hocg.rabbit.com.biz.pojo.dto.SendSystemMessageDto;
import in.hocg.rabbit.com.biz.pojo.ro.message.*;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageStatVo;
import in.hocg.rabbit.com.biz.manager.MessageUserRefProxyService;
import in.hocg.rabbit.com.biz.pojo.vo.message.scrollByChatUserVo;
import in.hocg.rabbit.com.biz.service.MessageUserRefService;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * [消息模块] 用户接收的消息表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Service
@Validated
@UseConvert(MessageConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MessageUserRefServiceImpl extends AbstractServiceImpl<MessageUserRefMapper, MessageUserRef> implements MessageUserRefService {
    private final MessageUserRefProxyService messageUserRefProxyService;
    private final UserServiceApi userServiceApi;
    private final MessageUserRefMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<MessageComplexVo> pagingWithSelf(MessagePagingRo ro) {
        IPage<MessageUserRef> result = baseMapper.paging(ro, ro.ofPage());
        if (ObjectUtil.defaultIfNull(ro.getMarkReady(), true)) {
            this.markReadById(LangUtils.toList(result.getRecords(), MessageUserRef::getId));
        }
        return as(result, MessageComplexVo.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendPersonalMessage(SendPersonalMessageRo ro) {
        SendPersonalMessageDto dto = mapping.asSendPersonalMessageDto(ro);
        messageUserRefProxyService.sendPersonalMessage(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendSystemMessage(SendSystemMessageRo ro) {
        Assert.isTrue(userServiceApi.isSuperAdminByUserId(ro.getUserId()));
        SendSystemMessageDto dto = mapping.asSendSystemMessageDto(ro);
        messageUserRefProxyService.sendSystemMessage(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MessageStatVo getMessageStatByUserId(Long userId) {
        MessageStatVo result = new MessageStatVo();
        if (Objects.isNull(userId)) {
            return result;
        }

        Long unreadNoticeCount = this.countByUnreadAndRefTypeAndUserId(MessageType.NoticeMessage.getCodeStr(), userId);
        Long unreadPersonalCount = this.countByUnreadAndRefTypeAndUserId(MessageType.PersonalMessage.getCodeStr(), userId);
        Long unreadSystemCount = this.countByUnreadAndRefTypeAndUserId(MessageType.SystemMessage.getCodeStr(), userId);

        result.setUnreadPersonCount(unreadPersonalCount);
        result.setUnreadNoticeCount(unreadNoticeCount);
        result.setUnreadSystemCount(unreadSystemCount);
        result.setUnreadTotalCount(unreadPersonalCount + unreadNoticeCount + unreadSystemCount);
        return result;
    }

    @Override
    public IScroll<MessageComplexVo> scroll(MessageScrollRo ro) {
        IPage<MessageUserRef> result = baseMapper.scroll(ro, ro.ofPage());
        if (ObjectUtil.defaultIfNull(ro.getMarkReady(), true)) {
            this.markReadById(LangUtils.toList(result.getRecords(), MessageUserRef::getId));
        }
        return as(PageUtils.fillScroll(result, CommonEntity::getId), MessageComplexVo.class);
    }

    @Override
    public IScroll<MessageComplexVo> scrollLastByChatUser(MessageByChatUserScrollRo ro) {
        List<Long> id = baseMapper.scrollLastByChatUser(ro, ro.ofPage()).getRecords().stream()
            .map(scrollByChatUserVo::getNextId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(id)) {
            return PageUtils.emptyScroll();
        }
        List<MessageUserRef> records = listByIds(id);
        boolean hasMore = records.size() == ro.getSize();
        return as(PageUtils.fillScroll(hasMore, records, CommonEntity::getId), MessageComplexVo.class);
    }

    @Override
    public IScroll<MessageComplexVo> scrollBySender(MessageByChatUserScrollRo ro) {
        IPage<MessageUserRef> result = baseMapper.scrollBySender(ro, ro.ofPage());
        if (ObjectUtil.defaultIfNull(ro.getMarkReady(), true)) {
            this.markReadById(LangUtils.toList(result.getRecords(), MessageUserRef::getId));
        }
        return as(PageUtils.fillScroll(result, CommonEntity::getId), MessageComplexVo.class);
    }

    private Long countByUnreadAndRefTypeAndUserId(String refType, Long userId) {
        return lambdaQuery().eq(MessageUserRef::getReceiverUser, userId)
            .eq(MessageUserRef::getMessageType, refType)
            .isNull(MessageUserRef::getReadAt).count();
    }

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void markReadById(List<Long> ids) {
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
