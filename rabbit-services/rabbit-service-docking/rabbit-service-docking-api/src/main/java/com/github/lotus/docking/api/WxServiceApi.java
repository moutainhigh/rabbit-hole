package com.github.lotus.docking.api;

import com.github.lotus.common.constant.GlobalConstant;
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
@FeignClient(value = ServiceName.NAME)
public interface WxServiceApi {

    String CONTEXT_ID = "WxServiceApi";

    @GetMapping(value = CONTEXT_ID + "/getQrCode", headers = GlobalConstant.FEIGN_HEADER)
    WxMpQrCodeVo getQrCode(@RequestParam("appid") String appid);

    @GetMapping(value = CONTEXT_ID + "/getWxLoginStatus", headers = GlobalConstant.FEIGN_HEADER)
    WxLoginInfoVo getWxLoginStatus(@RequestParam("idFlag") String idFlag);
}
