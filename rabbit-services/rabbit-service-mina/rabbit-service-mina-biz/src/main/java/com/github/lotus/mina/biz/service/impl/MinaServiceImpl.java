package com.github.lotus.mina.biz.service.impl;

import com.github.lotus.docking.api.WxMaServiceApi;
import com.github.lotus.mina.biz.service.MinaService;
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
public class MinaServiceImpl implements MinaService {
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
