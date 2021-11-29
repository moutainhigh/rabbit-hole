package in.hocg.rabbit.mina.biz.controller;

import in.hocg.rabbit.mina.biz.pojo.ro.BatchUploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.pojo.ro.UploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.support.ytb.YouTubeService;
import in.hocg.boot.web.autoconfiguration.utils.web.ResponseUtils;
import in.hocg.boot.web.result.Result;
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
    @GetMapping("/authorize")
    public ResponseEntity<Void> authorize(@RequestParam("clientId") String clientId, @RequestParam(value = "scopes", required = false, defaultValue = "https://www.googleapis.com/auth/youtube") List<String> scopes) {
        return ResponseUtils.found(service.authorize(clientId, scopes));
    }

    @ResponseBody
    @GetMapping("/{clientId}/callback")
    public Result<String> callback(@PathVariable String clientId, @RequestParam("code") String code, @RequestParam("scope") List<String> scopes) {
        service.authorizeCallback(clientId, scopes, code);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/{clientId}/upload")
    public Result<String> upload(@PathVariable String clientId, @RequestBody UploadYouTubeVideoRo ro) {
        service.uploadVideo(clientId, ro);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/{clientId}/upload/local")
    public Result<Void> uploadLocal(@PathVariable String clientId, @RequestBody BatchUploadYouTubeVideoRo ro) {
        service.uploadDir(clientId, ro);
        return Result.success();
    }

}
