package com.github.lotus.docking.api;

import com.github.lotus.docking.api.pojo.vo.WxLoginInfoVo;
import com.github.lotus.docking.api.pojo.vo.WxMpQrCodeVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = WxApi.CONTEXT_ID)
public interface WxApi {

    String CONTEXT_ID = "WxApi";

    @GetMapping(CONTEXT_ID + "/getQrCode")
    WxMpQrCodeVo getQrCode(@RequestParam("appid") String appid);

    @GetMapping(CONTEXT_ID + "/getWxLoginStatus")
    WxLoginInfoVo getWxLoginStatus(@RequestParam("idFlag") String idFlag);
}
