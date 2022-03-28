package in.hocg.rabbit.com.biz.service.impl;

import in.hocg.rabbit.com.biz.entity.UserSubscriberConfig;
import in.hocg.rabbit.com.biz.mapper.NoticeUserConfigMapper;
import in.hocg.rabbit.com.biz.service.UserSubscriberConfigService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [消息模块] 用户订阅配置表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserSubscriberConfigServiceImpl extends AbstractServiceImpl<NoticeUserConfigMapper, UserSubscriberConfig> implements UserSubscriberConfigService {

    @Override
    public List<UserSubscriberConfig> listByEventTypeAndRefIdAndRefType(String eventType, Long refId, String refType) {
        return lambdaQuery().eq(UserSubscriberConfig::getEventType, eventType)
            .eq(UserSubscriberConfig::getRefType, refType)
            .eq(UserSubscriberConfig::getRefId, refId).list();
    }
}
