package in.hocg.rabbit.docking.biz.support.wxmp.handler;

import in.hocg.rabbit.docking.biz.service.WxMpIndexService;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by hocgin on 2020/4/25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SubscriptionHandler extends AbstractHandler {
    private final WxMpIndexService service;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        final String appid = wxMpService.getWxMpConfigStorage().getAppId();
        String scene = wxMessage.getEventKey();
        final String fromUser = wxMessage.getFromUser();
        service.handleWxMpLoginOnSubscription(appid, fromUser, scene);
        return null;
    }
}
