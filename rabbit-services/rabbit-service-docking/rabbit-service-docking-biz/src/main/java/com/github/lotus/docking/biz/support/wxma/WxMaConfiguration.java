package com.github.lotus.docking.biz.support.wxma;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.message.WxMaMessageHandler;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.File;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WxMaConfiguration {

    @Bean @Lazy
    @ConditionalOnMissingBean(WxMpMessageRouter.class)
    public WxMaMessageRouter wxMaMessageRouter(WxMaService service) {
        final WxMaMessageRouter router = new WxMaMessageRouter(service);
        router
            .rule().handler(logHandler).next()
            .rule().async(false).content("订阅消息").handler(subscribeMsgHandler).end()
            .rule().async(false).content("文本").handler(textHandler).end()
            .rule().async(false).content("图片").handler(picHandler).end()
            .rule().async(false).content("二维码").handler(qrcodeHandler).end();
        return router;
    }

    private final WxMaMessageHandler subscribeMsgHandler = (wxMessage, context, service, sessionManager) -> {
        service.getMsgService().sendSubscribeMsg(WxMaSubscribeMessage.builder()
            .templateId("此处更换为自己的模板id")
            .data(Lists.newArrayList(
                new WxMaSubscribeMessage.Data("keyword1", "339208499")))
            .toUser(wxMessage.getFromUser())
            .build());
        return null;
    };

    private final WxMaMessageHandler logHandler = (wxMessage, context, service, sessionManager) -> {
        log.info("收到消息：" + wxMessage.toString());
        service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("收到信息为：" + wxMessage.toJson())
            .toUser(wxMessage.getFromUser()).build());
        return null;
    };

    private final WxMaMessageHandler textHandler = (wxMessage, context, service, sessionManager) -> {
        service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("回复文本消息")
            .toUser(wxMessage.getFromUser()).build());
        return null;
    };

    private final WxMaMessageHandler picHandler = (wxMessage, context, service, sessionManager) -> {
        try {
            WxMediaUploadResult uploadResult = service.getMediaService()
                .uploadMedia("image", "png",
                    ClassLoader.getSystemResourceAsStream("tmp.png"));
            service.getMsgService().sendKefuMsg(
                WxMaKefuMessage
                    .newImageBuilder()
                    .mediaId(uploadResult.getMediaId())
                    .toUser(wxMessage.getFromUser())
                    .build());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return null;
    };

    private final WxMaMessageHandler qrcodeHandler = (wxMessage, context, service, sessionManager) -> {
        try {
            final File file = service.getQrcodeService().createQrcode("123", 430);
            WxMediaUploadResult uploadResult = service.getMediaService().uploadMedia("image", file);
            service.getMsgService().sendKefuMsg(
                WxMaKefuMessage
                    .newImageBuilder()
                    .mediaId(uploadResult.getMediaId())
                    .toUser(wxMessage.getFromUser())
                    .build());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    };

}
