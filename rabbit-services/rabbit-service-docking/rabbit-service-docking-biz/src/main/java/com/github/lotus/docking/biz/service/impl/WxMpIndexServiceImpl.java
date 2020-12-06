package com.github.lotus.docking.biz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.lotus.chaos.api.modules.ums.SocialApi;
import com.github.lotus.docking.api.pojo.vo.WxLoginInfoVo;
import com.github.lotus.docking.api.pojo.vo.WxMpQrCodeVo;
import com.github.lotus.docking.biz.cache.WxMpCacheService;
import com.github.lotus.docking.biz.service.WxMpIndexService;
import com.github.lotus.docking.biz.support.wxmp.WxMpConfiguration;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
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
    private final SocialApi socialApi;

    @Override
    public WxMpQrCodeVo getWxMpQrcodeUrl(String appid) {
        WxMpService service = WxMpConfiguration.getMaService(appid);
        String idFlag = appid + "#" + IdUtil.randomUUID();
        WxMpQrCodeTicket ticket;
        wxMpCacheService.applyWxLoginKey(idFlag);
        try {
            ticket = service.getQrcodeService().qrCodeCreateTmpTicket(idFlag, 1000);
        } catch (WxErrorException e) {
            throw ServiceException.wrap(e);
        }
        return new WxMpQrCodeVo()
            .setIdFlag(idFlag)
            .setQrCodeUrl(ticket.getUrl());
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
        wxMpCacheService.updateWxLoginKey(scene, fromUserOpenid);
    }

}
