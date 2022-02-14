package in.hocg.rabbit.com.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by hocgin on 2022/2/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ComServiceName.NAME)
public interface ComServiceApi {


}
