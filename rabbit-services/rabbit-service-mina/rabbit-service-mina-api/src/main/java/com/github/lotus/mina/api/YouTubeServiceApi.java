package com.github.lotus.mina.api;

import com.github.lotus.common.constant.GlobalConstant;
import com.github.lotus.mina.api.pojo.ro.UploadYouTubeRo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME)
public interface YouTubeServiceApi {

    String CONTEXT_ID = "YouTubeServiceApi";

    @PostMapping(value = CONTEXT_ID + "/upload", headers = GlobalConstant.FEIGN_HEADER)
    String upload(@RequestBody UploadYouTubeRo ro);
}
