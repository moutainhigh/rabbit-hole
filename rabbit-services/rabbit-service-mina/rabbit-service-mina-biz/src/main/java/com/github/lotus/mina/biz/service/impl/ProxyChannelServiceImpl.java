package com.github.lotus.mina.biz.service.impl;

import com.github.lotus.common.utils.Rules;
import com.github.lotus.mina.biz.entity.ProxyChannel;
import com.github.lotus.mina.biz.manager.ProxyManager;
import com.github.lotus.mina.biz.mapper.ProxyChannelMapper;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelInfoVo;
import com.github.lotus.mina.biz.service.ProxyChannelService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.web.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * <p>
 * [代理] 用户隧道表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-11-16
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProxyChannelServiceImpl extends AbstractServiceImpl<ProxyChannelMapper, ProxyChannel>
    implements ProxyChannelService {
    private final ProxyManager proxyManager;

    @Override
    public ProxyChannelInfoVo getChannelInfo(String channelId) {
        ProxyChannel channel = lambdaQuery().eq(ProxyChannel::getChannelId, channelId)
            .eq(ProxyChannel::getEnabled, true).oneOpt()
            .orElseThrow(() -> ServiceException.wrap("渠道ID错误"));
        return proxyManager.getFrpChannel(channel);
    }

    private Optional<ProxyChannel> getByChannelId(String channelId) {
        return lambdaQuery().eq(ProxyChannel::getChannelId, channelId).oneOpt();
    }
}
