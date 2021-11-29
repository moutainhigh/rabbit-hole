package in.hocg.rabbit.mina.biz.manager.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import in.hocg.rabbit.mina.biz.cache.MinaCacheService;
import in.hocg.rabbit.mina.biz.entity.ProxyChannel;
import in.hocg.rabbit.mina.biz.manager.FrpManager;
import in.hocg.rabbit.mina.biz.service.ProxyChannelService;
import in.hocg.rabbit.mina.biz.support.channel.ChannelHelper;
import in.hocg.rabbit.mina.biz.support.channel.frp.ro.*;
import in.hocg.rabbit.mina.biz.support.channel.frp.vo.FrpResult;
import in.hocg.rabbit.mina.biz.support.channel.frp.vo.NewProxyFrpVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by hocgin on 2021/11/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FrpManagerImpl implements FrpManager {
    private final ProxyChannelService proxyChannelService;
    private final MinaCacheService cacheService;

    @Override
    public FrpResult<?> login(LoginFrpRo ro) {
        return FrpResult.pass();
    }

    @Override
    public FrpResult<?> newProxy(NewProxyFrpRo ro) {
        String channelId = ro.getProxyName();
        String type = ro.getProxyType();
        List<String> customDomains = ro.getCustomDomains();
        String subdomain = ro.getSubdomain();
        Integer remotePort = ro.getRemotePort();

        Optional<ProxyChannel> channelOpt = proxyChannelService.getByChannelIdAndEnabledOn(channelId);

        if (channelOpt.isEmpty()) {
            return FrpResult.reject(StrUtil.format("渠道ID={}错误，未找到代理渠道", channelId));
        }
        ProxyChannel channel = channelOpt.get();
        String domain = ChannelHelper.getDomain(channel);

        if (!customDomains.contains(domain)) {
            return FrpResult.reject(StrUtil.format("渠道ID={}错误，数据校验失败", channelId));
        }

        if (!StrUtil.equals(type, channel.getType())) {
            return FrpResult.reject(StrUtil.format("渠道ID={}错误，数据校验失败", channelId));
        }
        String runId = ro.getUser().getRunId();

        cacheService.putProxyChannel(runId);
        NewProxyFrpRo.Metas metas = ro.getMetas();

        NewProxyFrpVo content = BeanUtil.copyProperties(ro, NewProxyFrpVo.class);
        content.setProxyName(channelId);
        content.setProxyType(type);
        content.setSubdomain(subdomain);
        content.setRemotePort(remotePort);
        return FrpResult.pass(content);
    }

    @Override
    public FrpResult<?> ping(PingFrpRo ro) {
        return checkUserHeartbeat(ro.getUser());
    }

    @Override
    public FrpResult<?> newWorkConn(NewWorkConnFrpRo ro) {
        return FrpResult.pass();
    }

    @Override
    public FrpResult<?> newUserConn(NewUserConnFrpRo ro) {
        return checkUserHeartbeat(ro.getUser());
    }

    private FrpResult<?> checkUserHeartbeat(UserComFrpRo ro) {
        String runId = ro.getRunId();
        if (cacheService.hasProxyChannel(runId)) {
            return FrpResult.pass();
        }
        return FrpResult.reject("连接被服务端中断");
    }
}
