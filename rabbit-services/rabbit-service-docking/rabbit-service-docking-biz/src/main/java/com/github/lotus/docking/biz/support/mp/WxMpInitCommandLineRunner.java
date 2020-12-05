package com.github.lotus.docking.biz.support.mp;

import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by hocgin on 2020/4/26.
 * email: hocgin@gmail.com
 * 多微信配置初始化
 *
 * @author hocgin
 */
@Slf4j
@Order
@Component
@Import(WxProperties.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WxMpInitCommandLineRunner implements CommandLineRunner {
    private final WxProperties properties;
    private final WxMpService wxMpService;

    @Override
    public void run(String... args) {
        Map<String, WxMpConfigStorage> wxMpConfigMaps = Maps.newHashMap();
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        String appid = properties.getAppid();
        config.setAppId(appid);
        config.setSecret(properties.getSecret());
        config.setToken(properties.getToken());
        wxMpConfigMaps.put(appid, config);
        wxMpService.setMultiConfigStorages(wxMpConfigMaps, appid);
    }
}
