package in.hocg.rabbit.mall.api.named;

import in.hocg.boot.named.annotation.NamedService;
import in.hocg.rabbit.mall.api.MallServiceName;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by hocgin on 2021/12/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = MallServiceName.NAME)
public interface MallNamedServiceApi extends NamedService {


}
