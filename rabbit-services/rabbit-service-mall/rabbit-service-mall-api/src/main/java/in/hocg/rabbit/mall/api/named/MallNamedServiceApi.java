package in.hocg.rabbit.mall.api.named;

import in.hocg.boot.named.annotation.NamedService;
import in.hocg.boot.named.ifc.NamedArgs;
import in.hocg.boot.named.ifc.NamedHandler;
import in.hocg.rabbit.mall.api.MallServiceName;
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
@FeignClient(value = MallServiceName.NAME)
public interface MallNamedServiceApi extends NamedService {
    String CONTEXT_ID = "MallNamedServiceApi";

    @NamedHandler(MallNamedType.DataDictName)
    @PostMapping(value = CONTEXT_ID + "/loadByDataDictName", headers = MallServiceName.FEIGN_HEADER)
    Map<String, Object> loadByDataDictName(@RequestBody NamedArgs args);
}
