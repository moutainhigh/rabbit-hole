package com.github.lotus.docking.biz.support.wxmp;

import com.github.lotus.docking.biz.support.wxmp.handler.DebugReplyHandler;
import com.github.lotus.docking.biz.support.wxmp.handler.ScanHandler;
import com.github.lotus.docking.biz.support.wxmp.handler.SubscriptionHandler;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by hocgin on 2020/4/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpConfiguration {
    private final WxMpProperties wxMpProperties;
    private final WxMpService mpServices;

    @PostConstruct
    public void init() {
        Map<String, WxMpConfigStorage> wxMpConfigMaps = LangUtils.toMap(wxMpProperties.getConfigs(), WxMpProperties.Config::getAppid, config -> {
            WxMpDefaultConfigImpl result = new WxMpDefaultConfigImpl();
            result.setAppId(config.getAppid());
            result.setSecret(config.getSecret());
            result.setToken(config.getToken());
            result.setAesKey(config.getAesKey());
            return result;
        });
        mpServices.setMultiConfigStorages(wxMpConfigMaps);
    }

    @Bean
    @ConditionalOnMissingBean(WxMpService.class)
    public WxMpService mpService() {
        return new WxMpServiceImpl();
    }

    private final ScanHandler scanHandler;
    private final SubscriptionHandler subscriptionHandler;
    private final DebugReplyHandler debugReplyHandler;

    @Bean
    @ConditionalOnMissingBean(WxMpMessageRouter.class)
    public WxMpMessageRouter newRouter(WxMpService mpService) {
        return new WxMpMessageRouter(mpService)
            // 扫描进入
            .rule().async(false).msgType(WxConsts.XmlMsgType.EVENT).event(WxConsts.EventType.SCAN).handler(scanHandler).next()
            // 关注事件
            .rule().async(false).msgType(WxConsts.XmlMsgType.EVENT).event(WxConsts.EventType.SUBSCRIBE).handler(subscriptionHandler).next()
            // 取消关注事件
            .rule().async(false).msgType(WxConsts.XmlMsgType.EVENT).event(WxConsts.EventType.UNSUBSCRIBE).handler(null).next()
            .rule().async(false).content("芝麻开门").handler(debugReplyHandler)
            // 根据回复规则进行匹配
//            .next().rule().async(false).handler(ruleReplyHandler)
            .end();
    }
}
