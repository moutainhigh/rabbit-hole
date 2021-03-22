package com.github.lotus.docking.biz.support.wxmp;

import com.github.lotus.docking.biz.support.wxmp.handler.DebugReplyHandler;
import com.github.lotus.docking.biz.support.wxmp.handler.ScanHandler;
import com.github.lotus.docking.biz.support.wxmp.handler.SubscriptionHandler;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Created by hocgin on 2020/4/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WxMpConfiguration {
    private final ScanHandler scanHandler;
    private final SubscriptionHandler subscriptionHandler;
    private final DebugReplyHandler debugReplyHandler;

    @Bean @Lazy
    @ConditionalOnMissingBean(WxMpMessageRouter.class)
    public WxMpMessageRouter wxMpMessageRouter(WxMpService mpService) {
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
