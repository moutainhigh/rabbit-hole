package in.hocg.rabbit.docking.biz.apiimpl;

import in.hocg.rabbit.docking.api.WxServiceApi;
import in.hocg.rabbit.docking.api.pojo.vo.WxLoginInfoVo;
import in.hocg.rabbit.docking.api.pojo.vo.WxMpQrCodeVo;
import in.hocg.rabbit.docking.biz.service.WxMpIndexService;
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
public class WxServiceApiImpl implements WxServiceApi {
    private final WxMpIndexService service;

    @Override
    public WxMpQrCodeVo getQrCode(String appid) {
        return service.getWxMpQrcodeUrl(appid);
    }

    @Override
    public WxLoginInfoVo getWxLoginStatus(String idFlag) {
        return service.getWxLoginStatus(idFlag);
    }
}
