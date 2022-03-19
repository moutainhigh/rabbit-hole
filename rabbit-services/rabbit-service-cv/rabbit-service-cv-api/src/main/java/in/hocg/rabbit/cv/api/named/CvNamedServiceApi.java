package in.hocg.rabbit.cv.api.named;

import in.hocg.boot.named.annotation.NamedService;
import in.hocg.rabbit.cv.api.CvServiceName;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by hocgin on 2021/12/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = CvServiceName.NAME)
public interface CvNamedServiceApi extends NamedService {


}
