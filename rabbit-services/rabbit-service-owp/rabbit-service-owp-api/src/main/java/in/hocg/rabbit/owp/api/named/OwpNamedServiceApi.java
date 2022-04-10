package in.hocg.rabbit.owp.api.named;

import in.hocg.boot.named.annotation.NamedService;
import in.hocg.rabbit.owp.api.OwpServiceName;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * Created by hocgin on 2021/12/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = OwpServiceName.NAME)
public interface OwpNamedServiceApi extends NamedService {


}
