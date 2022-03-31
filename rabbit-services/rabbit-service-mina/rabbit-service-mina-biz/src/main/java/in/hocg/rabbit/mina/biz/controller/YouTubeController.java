package in.hocg.rabbit.mina.biz.controller;

import in.hocg.rabbit.mina.biz.pojo.ro.BatchUploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.pojo.ro.UploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.manager.YouTubeService;
import in.hocg.boot.web.autoconfiguration.utils.web.ResponseUtils;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/youtube")
public class YouTubeController {
    private final YouTubeService service;

    @ResponseBody
    @PostMapping("/upload")
    public Result<String> upload(@RequestBody UploadYouTubeVideoRo ro) {
        service.uploadVideo(ro);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/upload/local")
    public Result<Void> uploadLocal(@RequestBody BatchUploadYouTubeVideoRo ro) {
        service.uploadDir(ro);
        return Result.success();
    }

}
