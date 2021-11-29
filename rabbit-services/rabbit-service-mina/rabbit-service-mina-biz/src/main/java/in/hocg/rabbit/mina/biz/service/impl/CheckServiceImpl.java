package in.hocg.rabbit.mina.biz.service.impl;

import in.hocg.rabbit.docking.api.WxMaServiceApi;
import in.hocg.rabbit.mina.biz.service.CheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2021/1/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CheckServiceImpl implements CheckService {
    private final WxMaServiceApi wxMaServiceApi;

    @Override
    public boolean checkMessage(String appid, String text) {
        return wxMaServiceApi.checkMessage(appid, text);
    }

    @Override
    public boolean checkImage(String appid, String imageUrl) {
        return wxMaServiceApi.checkImage(appid, imageUrl);
    }
}
