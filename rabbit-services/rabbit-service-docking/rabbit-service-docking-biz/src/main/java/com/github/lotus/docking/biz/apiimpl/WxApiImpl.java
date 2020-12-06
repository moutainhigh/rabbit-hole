package com.github.lotus.docking.biz.apiimpl;

import com.github.lotus.docking.api.WxApi;
import com.github.lotus.docking.api.pojo.vo.WxMpQrCodeVo;
import com.github.lotus.docking.biz.service.WxMpIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class WxApiImpl implements WxApi {
    private final WxMpIndexService service;

    @Override
    public WxMpQrCodeVo getQrCode(String appid) {
        return service.getWxMpQrcodeUrl(appid);
    }
}
