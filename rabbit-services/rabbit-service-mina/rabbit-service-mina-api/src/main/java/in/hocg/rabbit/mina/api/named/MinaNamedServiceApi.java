package in.hocg.rabbit.mina.api.named;

import in.hocg.boot.named.annotation.NamedService;
import in.hocg.rabbit.mina.api.MinaServiceName;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by hocgin on 2021/12/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = MinaServiceName.NAME)
public interface MinaNamedServiceApi extends NamedService {


}
