package in.hocg.rabbit.mina.biz.controller;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mina.biz.manager.YouTubeService;
import in.hocg.rabbit.mina.biz.pojo.ro.BatchUploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.pojo.ro.UploadYouTubeVideoRo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Validated
@RestController
@Api(tags = "mina::Y2B.功能")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/youtube/feature")
public class YouTubeFeatureController {
    private final YouTubeService service;

    @PostMapping("/{channelId}/upload")
    public Result<String> upload(@PathVariable Long channelId, @RequestBody UploadYouTubeVideoRo ro) {
        service.uploadVideo(channelId, ro);
        return Result.success();
    }

    @PostMapping("/{channelId}/upload/local")
    public Result<Void> uploadDir(@PathVariable Long channelId, @RequestBody BatchUploadYouTubeVideoRo ro) {
        service.uploadDir(channelId, ro);
        return Result.success();
    }

}
