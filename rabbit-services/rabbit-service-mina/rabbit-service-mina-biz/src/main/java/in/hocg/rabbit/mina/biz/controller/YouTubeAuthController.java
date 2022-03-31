package in.hocg.rabbit.mina.biz.controller;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.boot.web.autoconfiguration.utils.web.ResponseUtils;
import in.hocg.rabbit.mina.biz.manager.YouTubeService;
import in.hocg.rabbit.mina.biz.pojo.ro.BatchUploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.pojo.ro.UploadYouTubeVideoRo;
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
public class YouTubeAuthController {
    private final YouTubeService service;

    @ResponseBody
    @GetMapping("/authorize")
    public ResponseEntity<Void> authorize(@RequestParam("clientId") String clientId,
                                          @RequestParam(value = "scopes", required = false, defaultValue = "https://www.googleapis.com/auth/youtube") List<String> scopes) {
        return ResponseUtils.found(service.authorize(clientId, scopes));
    }

    @ResponseBody
    @GetMapping("/{clientId}/callback")
    public Result<String> callback(@PathVariable String clientId, @RequestParam("code") String code,
                                   @RequestParam("scope") List<String> scopes) {
        service.authorizeCallback(clientId, scopes, code);
        return Result.success();
    }

}
