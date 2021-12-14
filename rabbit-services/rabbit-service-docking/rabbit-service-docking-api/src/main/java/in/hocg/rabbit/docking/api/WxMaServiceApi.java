package in.hocg.rabbit.docking.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2021/1/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = DockingServiceName.NAME)
public interface WxMaServiceApi {
    String CONTEXT_ID = "WxMaServiceApi";

    @PostMapping(value = CONTEXT_ID + "/checkMessage", headers = DockingServiceName.FEIGN_HEADER)
    boolean checkMessage(@RequestParam("appid") String appid, @RequestParam("text") String text);

    @PostMapping(value = CONTEXT_ID + "/checkImage", headers = DockingServiceName.FEIGN_HEADER)
    boolean checkImage(@RequestParam("appid") String appid, @RequestParam("imageUrl") String imageUrl);
}
