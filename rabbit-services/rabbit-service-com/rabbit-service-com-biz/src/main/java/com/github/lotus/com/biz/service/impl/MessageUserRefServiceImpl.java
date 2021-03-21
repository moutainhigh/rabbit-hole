package com.github.lotus.com.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.MessageUserRef;
import com.github.lotus.com.biz.mapper.MessageUserRefMapper;
import com.github.lotus.com.biz.pojo.ro.message.MessagePagingRo;
import com.github.lotus.com.biz.service.MessageUserRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

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

    @Override
    public IPage<MessageUserRef> paging(MessagePagingRo ro) {
        return baseMapper.paging(ro);
    }
}
