package com.github.lotus.com.biz.service.impl;

import com.github.lotus.com.biz.entity.SystemMessage;
import com.github.lotus.com.biz.mapper.SystemMessageMapper;
import com.github.lotus.com.biz.mapstruct.SystemMessageMapping;
import com.github.lotus.com.biz.pojo.dto.SendSystemMessageDto;
import com.github.lotus.com.biz.service.SystemMessageService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * <p>
 * [消息模块] 系统消息表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SystemMessageServiceImpl extends AbstractServiceImpl<SystemMessageMapper, SystemMessage>
    implements SystemMessageService {
    private final SystemMessageMapping mapping;

}
