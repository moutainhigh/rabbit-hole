package com.github.lotus.chaos.biz.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.nacos.common.utils.Md5Utils;
import com.github.lotus.chaos.biz.pojo.vo.ProxyChannelInfoVo;
import com.github.lotus.chaos.biz.service.ProxyService;
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
public class ProxyServiceImpl implements ProxyService {

    @Override
    public ProxyChannelInfoVo getChannelInfo(String channelId) {
        ProxyChannelInfoVo.ProxyConfig config = new ProxyChannelInfoVo.ProxyConfig()
            .setServerAddr("frps.hocgin.top")
            .setServerPort(30902)
            .setProxies(List.of(new ProxyChannelInfoVo.ProxyConfig.Proxy()
                .setName(channelId)
                .setType("http")
                .setCustomDomains("forward.hocgin.top")
                .setLocalIp("127.0.0.1")
                .setLocalPort(18987))
            );
        return new ProxyChannelInfoVo()
            .setMd5(Md5Utils.getMD5(JSONUtil.toJsonStr(config), "utf-8"))
            .setConfig(config);
    }

}
