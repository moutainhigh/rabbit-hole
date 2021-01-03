package com.github.lotus.docking.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2021/1/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = WxMaServiceApi.CONTEXT_ID)
public interface WxMaServiceApi {
    String CONTEXT_ID = "WxMaServiceApi";

    @PostMapping(CONTEXT_ID + "/checkMessage")
    boolean checkMessage(@RequestParam("appid") String appid,
                         @RequestParam("text") String text);

    @PostMapping(CONTEXT_ID + "/checkImage")
    boolean checkImage(@RequestParam("appid") String appid,
                       @RequestParam("imageUrl") String imageUrl);
}
