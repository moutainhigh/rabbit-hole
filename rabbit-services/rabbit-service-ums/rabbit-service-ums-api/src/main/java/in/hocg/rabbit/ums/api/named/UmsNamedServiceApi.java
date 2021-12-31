package in.hocg.rabbit.ums.api.named;

import in.hocg.boot.named.annotation.NamedService;
import in.hocg.rabbit.ums.api.UmsServiceName;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by hocgin on 2021/12/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = UmsServiceName.NAME)
public interface UmsNamedServiceApi extends NamedService {


}
