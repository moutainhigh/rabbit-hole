package in.hocg.rabbit.com.api;

import in.hocg.rabbit.common.constant.GlobalConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME)
public interface SnCodeServiceApi {
    String CONTEXT_ID = "SnCodeServiceApi";

    @PostMapping(value = CONTEXT_ID + "/getSnCode", headers = GlobalConstant.FEIGN_HEADER)
    String getSnCode(@RequestParam("groupCode") String groupCode);
}
