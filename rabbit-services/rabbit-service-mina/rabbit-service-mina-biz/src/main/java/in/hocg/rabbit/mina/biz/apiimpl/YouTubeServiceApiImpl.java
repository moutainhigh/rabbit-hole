package in.hocg.rabbit.mina.biz.apiimpl;

import in.hocg.rabbit.mina.api.YouTubeServiceApi;
import in.hocg.rabbit.mina.api.pojo.ro.UploadYouTubeRo;
import in.hocg.rabbit.mina.biz.support.ytb.YouTubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class YouTubeServiceApiImpl implements YouTubeServiceApi {
    private final YouTubeService service;

    @Override
    public String upload(UploadYouTubeRo ro) {
        return null;
    }
}
