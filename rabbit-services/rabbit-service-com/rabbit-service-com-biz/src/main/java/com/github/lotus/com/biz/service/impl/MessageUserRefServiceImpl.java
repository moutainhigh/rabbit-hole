package com.github.lotus.com.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.MessageUserRef;
import com.github.lotus.com.biz.mapper.MessageUserRefMapper;
import com.github.lotus.com.biz.mapstruct.MessageUserRefMapping;
import com.github.lotus.com.biz.pojo.dto.SendPersonalMessageDto;
import com.github.lotus.com.biz.pojo.ro.message.MessagePagingRo;
import com.github.lotus.com.biz.pojo.ro.message.SendPersonalMessageRo;
import com.github.lotus.com.biz.pojo.vo.message.MessageComplexVo;
import com.github.lotus.com.biz.service.MessageUserRefProxyService;
import com.github.lotus.com.biz.service.MessageUserRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

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
    public void readById(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        lambdaUpdate().in(MessageUserRef::getId, ids)
            .set(MessageUserRef::getReadAt, LocalDateTime.now())
            .update();
    }
}
