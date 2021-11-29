package in.hocg.rabbit.com.biz.service.impl;

import in.hocg.rabbit.com.biz.entity.NoticeUserConfig;
import in.hocg.rabbit.com.biz.mapper.NoticeUserConfigMapper;
import in.hocg.rabbit.com.biz.service.NoticeUserConfigService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
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
public class NoticeUserConfigServiceImpl extends AbstractServiceImpl<NoticeUserConfigMapper, NoticeUserConfig> implements NoticeUserConfigService {

    @Override
    public List<NoticeUserConfig> listByEventTypeAndRefIdAndRefType(String eventType, Long refId, String refType) {
        return lambdaQuery().eq(NoticeUserConfig::getEventType, eventType)
            .eq(NoticeUserConfig::getRefType, refType)
            .eq(NoticeUserConfig::getRefId, refId).list();
    }
}
