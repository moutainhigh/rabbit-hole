package com.github.lotus.docking.biz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.lotus.docking.api.pojo.vo.WxMpQrCodeVo;
import com.github.lotus.docking.biz.service.WxMpIndexService;
import com.github.lotus.docking.biz.support.wxmp.WxMpConfiguration;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/12/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WxMpIndexServiceImpl implements WxMpIndexService {

    @Override
    public WxMpQrCodeVo getWxMpQrcodeUrl(String appid) {
        String idFlag = IdUtil.randomUUID();
        WxMpService service = WxMpConfiguration.getMaService(appid);
        WxMpQrCodeTicket ticket;
        try {
            ticket = service.getQrcodeService().qrCodeCreateTmpTicket(idFlag, 1000);
        } catch (WxErrorException e) {
            throw ServiceException.wrap(e);
        }
        return new WxMpQrCodeVo()
            .setIdFlag(idFlag)
            .setQrCodeUrl(ticket.getUrl());
    }

}
