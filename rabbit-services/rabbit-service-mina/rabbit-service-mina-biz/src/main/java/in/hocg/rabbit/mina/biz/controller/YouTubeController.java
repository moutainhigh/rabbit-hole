package in.hocg.rabbit.mina.biz.controller;

import in.hocg.rabbit.mina.biz.pojo.ro.BatchUploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.pojo.ro.ClientYouTubeCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.UploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.manager.YouTubeService;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mina.biz.pojo.vo.YouTubeClientVo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Validated
@RestController
@Api(tags = "mina::Y2B.通用")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/youtube")
public class YouTubeController {
    private final YouTubeService service;

    @PostMapping("/client/_complete")
    public Result<List<YouTubeClientVo>> complete(@RequestBody ClientYouTubeCompleteRo ro) {
        return Result.success(service.clientComplete(ro));
    }

    @GetMapping("/{clientId}/callback")
    public Result<String> callback(@PathVariable String clientId,
                                   @RequestParam("code") String code, @RequestParam("scope") List<String> scopes) {
        service.authorizeCallback(clientId, scopes, code);
        return Result.success();
    }

}
