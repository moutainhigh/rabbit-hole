package in.hocg.rabbit.tpl.api.named;

import in.hocg.boot.named.annotation.NamedService;
import in.hocg.boot.named.ifc.NamedArgs;
import in.hocg.boot.named.ifc.NamedHandler;
import in.hocg.rabbit.tpl.api.TplServiceName;
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
@FeignClient(value = TplServiceName.NAME)
public interface TplNamedServiceApi extends NamedService {


}
