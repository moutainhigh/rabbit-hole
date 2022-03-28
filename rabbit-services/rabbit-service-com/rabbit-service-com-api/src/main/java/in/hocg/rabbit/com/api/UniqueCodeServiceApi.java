package in.hocg.rabbit.com.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ComServiceName.NAME)
public interface UniqueCodeServiceApi {
    String CONTEXT_ID = "SnCodeServiceApi";

    @PostMapping(value = CONTEXT_ID + "/getSnCode", headers = ComServiceName.FEIGN_HEADER)
    String getUniqueCode(@RequestParam("groupCode") String groupCode);
}
