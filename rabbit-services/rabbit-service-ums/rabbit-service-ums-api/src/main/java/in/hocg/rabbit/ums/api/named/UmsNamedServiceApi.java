package in.hocg.rabbit.ums.api.named;

import in.hocg.boot.named.annotation.NamedService;
import in.hocg.boot.named.ifc.NamedArgs;
import in.hocg.boot.named.ifc.NamedHandler;
import in.hocg.rabbit.ums.api.UmsServiceName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Created by hocgin on 2021/12/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = UmsServiceName.NAME)
public interface UmsNamedServiceApi extends NamedService {
    String CONTEXT_ID = "UmsNamedServiceApi";

    @NamedHandler(UmsNamedType.Username)
    @PostMapping(value = CONTEXT_ID + "/loadByUserId", headers = UmsServiceName.FEIGN_HEADER)
    Map<String, Object> loadByUserId(@RequestBody NamedArgs args);

}
