package com.github.lotus.wx.biz.mp;

import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.CommandLineRunner;
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
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WxMpInitCommandLineRunner implements CommandLineRunner {
    private final WxMpService wxMpService;

    @Override
    public void run(String... args) {
        Map<String, WxMpConfigStorage> wxMpConfigMaps = Maps.newHashMap();
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId("wx6c554c6d25b19c4a");
        config.setSecret("fe8e31b3bc4038ae5b0870a94fa5ed77");
        config.setToken("wx0hocgin");
        wxMpConfigMaps.put("wx6c554c6d25b19c4a", config);
        wxMpService.setMultiConfigStorages(wxMpConfigMaps, "wx6c554c6d25b19c4a");


    }
}
