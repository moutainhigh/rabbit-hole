package com.github.lotus.chaos.module.wl.service.impl;

import com.github.lotus.chaos.module.wl.entity.LogisticsLine;
import com.github.lotus.chaos.module.wl.mapper.LogisticsLineMapper;
import com.github.lotus.chaos.module.wl.service.LogisticsLineService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [物流模块] 物流线路表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LogisticsLineServiceImpl extends AbstractServiceImpl<LogisticsLineMapper, LogisticsLine> implements LogisticsLineService {

}
