package in.hocg.rabbit.com.biz.service.impl;

import in.hocg.rabbit.com.biz.entity.SystemMessage;
import in.hocg.rabbit.com.biz.mapper.SystemMessageMapper;
import in.hocg.rabbit.com.biz.mapstruct.SystemMessageMapping;
import in.hocg.rabbit.com.biz.service.SystemMessageService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
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
