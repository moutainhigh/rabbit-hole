package in.hocg.rabbit.docking.api;

import in.hocg.rabbit.docking.api.pojo.vo.WxLoginInfoVo;
import in.hocg.rabbit.docking.api.pojo.vo.WxMpQrCodeVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = DockingServiceName.NAME)
public interface WxServiceApi {

    String CONTEXT_ID = "WxServiceApi";

    @GetMapping(value = CONTEXT_ID + "/getQrCode", headers = DockingServiceName.FEIGN_HEADER)
    WxMpQrCodeVo getQrCode(@RequestParam("appid") String appid);

    @GetMapping(value = CONTEXT_ID + "/getWxLoginStatus", headers = DockingServiceName.FEIGN_HEADER)
    WxLoginInfoVo getWxLoginStatus(@RequestParam("idFlag") String idFlag);
}
