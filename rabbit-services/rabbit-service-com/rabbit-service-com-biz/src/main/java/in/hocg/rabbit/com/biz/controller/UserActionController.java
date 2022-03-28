package in.hocg.rabbit.com.biz.controller;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.com.biz.manager.MinaService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2021/6/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/action")
public class UserActionController {
    private final MinaService service;

    @ApiOperation("签到")
    @PostMapping("/sign")
    public Result<Void> sign() {
        UserContextHolder.getUserId().ifPresent(service::userSign);
        return Result.success();
    }

    @ApiOperation("观看视频")
    @PostMapping("/watch-ad")
    public Result<Void> watchAd() {
        UserContextHolder.getUserId().ifPresent(service::triggerWatchAd);
        return Result.success();
    }
}
