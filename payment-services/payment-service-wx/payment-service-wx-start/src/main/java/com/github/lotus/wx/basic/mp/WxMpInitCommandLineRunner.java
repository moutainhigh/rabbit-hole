package com.github.lotus.wx.basic.mp;

import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
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
        wxMpService.setMultiConfigStorages(wxMpConfigMaps, "default");
    }
}
