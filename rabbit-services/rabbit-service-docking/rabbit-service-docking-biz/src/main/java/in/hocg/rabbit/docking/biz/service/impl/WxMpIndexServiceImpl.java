package in.hocg.rabbit.docking.biz.service.impl;

import cn.hutool.core.util.IdUtil;
import in.hocg.rabbit.docking.api.pojo.vo.WxLoginInfoVo;
import in.hocg.rabbit.docking.api.pojo.vo.WxMpQrCodeVo;
import in.hocg.rabbit.docking.biz.cache.WxMpCacheService;
import in.hocg.rabbit.docking.biz.service.WxMpIndexService;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by hocgin on 2020/12/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WxMpIndexServiceImpl implements WxMpIndexService {
    private final WxMpCacheService wxMpCacheService;
    private final WxMpService wxMpService;

    @Override
    public WxMpQrCodeVo getWxMpQrcodeUrl(String appid) {
        WxMpService service = wxMpService.switchoverTo(appid);
        String idFlag = appid + "#" + IdUtil.randomUUID();
        WxMpQrCodeTicket ticket;
        wxMpCacheService.applyWxLoginKey(idFlag);
        String qrCodeUrl;
        int expireSeconds = 1000;
        try {
            ticket = service.getQrcodeService().qrCodeCreateTmpTicket(idFlag, expireSeconds);
            qrCodeUrl = service.getQrcodeService().qrCodePictureUrl(ticket.getTicket());
        } catch (WxErrorException e) {
            throw ServiceException.wrap(e);
        }
        return new WxMpQrCodeVo()
            .setIdFlag(idFlag)
            .setQrCodeUrl(qrCodeUrl);
    }

    @Override
    public WxLoginInfoVo getWxLoginStatus(String idFlag) {
        WxLoginInfoVo result = new WxLoginInfoVo();
        String openid = wxMpCacheService.getWxLoginKey(idFlag);
        if (Strings.isNotBlank(openid)) {
            result.setOpenid(openid);
            result.setStatus(WxLoginInfoVo.WxLoginStatus.Success);
        } else if (Objects.isNull(openid)) {
            result.setStatus(WxLoginInfoVo.WxLoginStatus.Fail);
        } else {
            result.setStatus(WxLoginInfoVo.WxLoginStatus.Processing);
        }
        return result;
    }

    @Override
    public void handleWxMpLoginOnSubscription(String appid, String fromUserOpenid, String scene) {
        WxMpService service = wxMpService.switchoverTo(appid);
        WxMpUser wxMpUser;
        try {
            wxMpUser = service.getUserService().userInfo(fromUserOpenid);
        } catch (WxErrorException e) {
            throw ServiceException.wrap(e);
        }
        if (wxMpUser.getSubscribe()) {
            wxMpCacheService.updateWxLoginKey(scene, fromUserOpenid);
        }
    }

}
