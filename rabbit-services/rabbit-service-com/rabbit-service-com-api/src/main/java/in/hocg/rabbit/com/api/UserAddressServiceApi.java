package in.hocg.rabbit.com.api;

import in.hocg.rabbit.com.api.pojo.vo.UserAddressFeignVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2022/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ComServiceName.NAME)
public interface UserAddressServiceApi {
    String CONTEXT_ID = "UserAddressServiceApi";

    @PostMapping(value = CONTEXT_ID + "/getDefaultByUserIdAndType", headers = ComServiceName.FEIGN_HEADER)
    UserAddressFeignVo getDefaultByUserIdAndType(@RequestParam("userId") Long userId, @RequestParam("type") String type);

    @PostMapping(value = CONTEXT_ID + "/getById", headers = ComServiceName.FEIGN_HEADER)
    UserAddressFeignVo getById(@RequestParam("id") Long id);
}
