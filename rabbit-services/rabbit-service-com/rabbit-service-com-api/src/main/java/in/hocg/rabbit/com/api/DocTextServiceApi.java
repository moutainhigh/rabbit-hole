package in.hocg.rabbit.com.api;

import in.hocg.rabbit.com.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.com.api.pojo.ro.UploadFileRo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by hocgin on 2022/2/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ComServiceName.NAME)
public interface DocTextServiceApi {
    String CONTEXT_ID = "DocTextServiceApi";

    @PostMapping(value = CONTEXT_ID + "/publish", headers = ComServiceName.FEIGN_HEADER)
    void publish(@Validated @RequestBody PublishDocTextRo ro);

}
