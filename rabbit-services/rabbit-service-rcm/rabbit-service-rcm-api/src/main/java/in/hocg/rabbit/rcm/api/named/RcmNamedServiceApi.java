package in.hocg.rabbit.rcm.api.named;

import in.hocg.boot.named.annotation.NamedService;
import in.hocg.boot.named.ifc.NamedArgs;
import in.hocg.boot.named.ifc.NamedHandler;
import in.hocg.rabbit.rcm.api.RcmServiceName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Created by hocgin on 2020/11/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = RcmServiceName.NAME)
public interface RcmNamedServiceApi extends NamedService {
    String CONTEXT_ID = "RcmNamedServiceApi";

    @NamedHandler(RcmNamedType.DataDictName)
    @PostMapping(value = CONTEXT_ID + "/loadByDataDict", headers = RcmServiceName.FEIGN_HEADER)
    Map<String, Object> loadByDataDict(@RequestBody NamedArgs args);

}
