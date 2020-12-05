package com.github.lotus.docking.biz.service.impl;

import com.github.lotus.docking.biz.service.WxIndexService;
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
public class WxIndexServiceImpl implements WxIndexService {
    private WxMpService wxMpService;

    @Override
    public String getWxMpQrcodeUrl() {
        WxMpQrCodeTicket ticket;
        try {
            ticket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket("", 1000);
        } catch (WxErrorException e) {
            throw ServiceException.wrap(e);
        }
        return ticket.getUrl();
    }

}
