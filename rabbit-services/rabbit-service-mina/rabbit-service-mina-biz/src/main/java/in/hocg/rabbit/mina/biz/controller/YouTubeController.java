package in.hocg.rabbit.mina.biz.controller;

import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.autoconfiguration.utils.web.ResponseUtils;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeClientCompleteRo;
import in.hocg.rabbit.mina.biz.manager.YouTubeService;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mina.biz.pojo.vo.YouTubeClientVo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@Api(tags = "mina::Y2B.通用")
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/youtube")
public class YouTubeController {
    private final YouTubeService service;

    @ResponseBody
    @UseLogger("检索 - 服务提供者")
    @PostMapping("/client/_complete")
    public Result<List<YouTubeClientVo>> complete(@RequestBody YouTubeClientCompleteRo ro) {
        return Result.success(service.clientComplete(ro));
    }

    @ResponseBody
    @UseLogger("授权频道 - 服务提供者")
    @GetMapping("/authorize")
    public Result<String> authorize(@RequestParam("clientId") String clientId,
                                    @RequestParam(value = "scopes", required = false, defaultValue = "https://www.googleapis.com/auth/youtube") List<String> scopes) {
        return Result.success(service.authorize(clientId, scopes));
    }

    @ResponseBody
    @UseLogger("确认授权 - 服务提供者")
    @GetMapping("/{clientId}/callback")
    public Result<String> callback(@PathVariable String clientId,
                                   @RequestParam("code") String code, @RequestParam("scope") List<String> scopes) {
        service.authorizeCallback(clientId, scopes, code);
        return Result.success();
    }

}
