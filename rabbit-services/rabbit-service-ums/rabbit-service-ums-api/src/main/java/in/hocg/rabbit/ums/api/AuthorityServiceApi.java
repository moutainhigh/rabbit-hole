package in.hocg.rabbit.ums.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2021/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = UmsServiceName.NAME)
public interface AuthorityServiceApi {
    String CONTEXT_ID = "AuthorityServiceApi";

    @PostMapping(value = CONTEXT_ID + "/isPassAuthorize", headers = UmsServiceName.FEIGN_HEADER)
    boolean isPassAuthorize(@RequestParam("username") String username, @RequestParam("servicePrefix") String servicePrefix,
                            @RequestParam("methodName") String methodName, @RequestParam("uri") String uri);
}
