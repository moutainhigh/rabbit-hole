package in.hocg.rabbit.docking.api.named;

import in.hocg.boot.named.annotation.NamedService;
import in.hocg.rabbit.docking.api.DockingServiceName;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by hocgin on 2021/12/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = DockingServiceName.NAME)
public interface DockingNamedServiceApi extends NamedService {


}
