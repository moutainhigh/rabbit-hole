package com.github.lotus.mina.biz.manager.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.nacos.common.utils.Md5Utils;
import com.github.lotus.mina.biz.entity.ProxyChannel;
import com.github.lotus.mina.biz.manager.ProxyManager;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelInfoVo;
import com.github.lotus.mina.biz.support.frp.FrpHelper;
import in.hocg.boot.utils.IpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hocgin on 2021/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProxyManagerImpl implements ProxyManager {

    @Override
    public ProxyChannelInfoVo getFrpChannel(ProxyChannel channel) {
        ProxyChannelInfoVo.ProxyConfig config = new ProxyChannelInfoVo.ProxyConfig()
            .setServerAddr("frps.hocgin.top")
            .setServerPort(30902)
//            本地开发环境
//            .setServerAddr("127.0.0.1")
//            .setServerPort(7000)
            .setProxies(List.of(new ProxyChannelInfoVo.ProxyConfig.Proxy()
                .setName(channel.getChannelId())
                .setType(channel.getType())
                .setCustomDomains(channel.getCustomDomains())
                .setLocalIp(IpUtils.longToIp(channel.getLocalIp()))
                .setLocalPort(channel.getLocalPort()))
            );
        return new ProxyChannelInfoVo()
            .setMd5(Md5Utils.getMD5(JSONUtil.toJsonStr(config), "utf-8"))
            .setConfig(config)
            .setCnvStr(FrpHelper.toFrpcIni(config));
    }

}
