package in.hocg.rabbit.docking.biz.apiimpl;

import in.hocg.rabbit.docking.api.WxMaServiceApi;
import in.hocg.rabbit.docking.biz.service.WxMaIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2021/1/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class WxMaServiceApiImpl implements WxMaServiceApi {
    private final WxMaIndexService service;

    @Override
    public boolean checkMessage(String appid, String text) {
        return service.checkMessage(appid, text);
    }

    @Override
    public boolean checkImage(String appid, String imageUrl) {
        return service.checkImage(appid, imageUrl);
    }
}
