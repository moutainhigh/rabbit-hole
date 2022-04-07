package in.hocg.rabbit.mina.api.named;

import in.hocg.boot.named.annotation.NamedService;
import in.hocg.boot.named.ifc.NamedArgs;
import in.hocg.boot.named.ifc.NamedHandler;
import in.hocg.rabbit.mina.api.MinaServiceName;
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
@FeignClient(value = MinaServiceName.NAME)
public interface MinaNamedServiceApi extends NamedService {

    String CONTEXT_ID = "MinaNamedServiceApi";

    @NamedHandler(MinaNamedType.DataDictName)
    @PostMapping(value = CONTEXT_ID + "/loadByDataDict", headers = MinaServiceName.FEIGN_HEADER)
    Map<String, Object> loadByDataDictName(@RequestBody NamedArgs args);
}
