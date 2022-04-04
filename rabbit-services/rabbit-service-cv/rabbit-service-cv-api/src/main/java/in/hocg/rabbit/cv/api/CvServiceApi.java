package in.hocg.rabbit.cv.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hocgin on 2022/4/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = CvServiceName.NAME)
public interface CvServiceApi {
    String CONTEXT_ID = "CvServiceApi";

    @PostMapping(value = CONTEXT_ID + "/mergeFile", headers = CvServiceName.FEIGN_HEADER)
    String mergeVideo(@RequestParam("files") List<String> files, @RequestParam("outputFile") String outputFile);
}
