package in.hocg.rabbit.com.biz.service;

import in.hocg.rabbit.com.biz.entity.UserSubscriberConfig;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;

/**
 * <p>
 * [消息模块] 用户订阅配置表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
public interface UserSubscriberConfigService extends AbstractService<UserSubscriberConfig> {

    List<UserSubscriberConfig> listByEventTypeAndRefIdAndRefType(String eventType, Long refId, String refType);
}
