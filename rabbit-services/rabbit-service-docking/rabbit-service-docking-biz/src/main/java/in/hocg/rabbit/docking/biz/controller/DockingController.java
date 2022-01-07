package in.hocg.rabbit.docking.biz.controller;


import in.hocg.rabbit.docking.biz.service.WxMpIndexService;
import in.hocg.boot.utils.struct.result.Result;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-08-21
 */
@Api(tags = {"docking::通用"})
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping
public class DockingController {
    private final WxMpIndexService indexService;

    @GetMapping("/worked")
    public Result<String> worked() {
        return Result.success();
    }

}

