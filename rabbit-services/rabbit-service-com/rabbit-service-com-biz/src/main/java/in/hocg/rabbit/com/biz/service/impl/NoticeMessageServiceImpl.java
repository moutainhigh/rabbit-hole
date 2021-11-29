package in.hocg.rabbit.com.biz.service.impl;

import in.hocg.rabbit.com.biz.entity.NoticeMessage;
import in.hocg.rabbit.com.biz.mapper.NoticeMessageMapper;
import in.hocg.rabbit.com.biz.service.NoticeMessageService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [消息模块] 订阅消息表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class NoticeMessageServiceImpl extends AbstractServiceImpl<NoticeMessageMapper, NoticeMessage> implements NoticeMessageService {

}
