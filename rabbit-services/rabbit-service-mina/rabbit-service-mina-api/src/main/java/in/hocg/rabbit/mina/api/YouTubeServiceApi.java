package in.hocg.rabbit.mina.api;

import in.hocg.rabbit.mina.api.pojo.ro.UploadYouTubeRo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = MinaServiceName.NAME)
public interface YouTubeServiceApi {

    String CONTEXT_ID = "YouTubeServiceApi";

    @PostMapping(value = CONTEXT_ID + "/upload", headers = MinaServiceName.FEIGN_HEADER)
    String upload(@RequestBody UploadYouTubeRo ro);
}
