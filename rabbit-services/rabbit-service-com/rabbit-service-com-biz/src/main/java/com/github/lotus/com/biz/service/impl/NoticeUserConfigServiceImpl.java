package com.github.lotus.com.biz.service.impl;

import com.github.lotus.com.biz.entity.NoticeUserConfig;
import com.github.lotus.com.biz.mapper.NoticeUserConfigMapper;
import com.github.lotus.com.biz.service.NoticeUserConfigService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

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

}
