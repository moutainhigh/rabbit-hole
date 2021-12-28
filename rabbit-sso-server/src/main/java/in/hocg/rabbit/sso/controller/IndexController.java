package in.hocg.rabbit.sso.controller;

import in.hocg.boot.web.result.Result;
import in.hocg.rabbit.sso.service.IndexService;
import in.hocg.rabbit.ums.api.UmsServiceName;
import in.hocg.rabbit.ums.api.pojo.ro.ForgotRo;
import in.hocg.rabbit.ums.api.pojo.ro.RegisterRo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2021/12/28
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = "sso::顶部")
@RestController
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IndexController {
    private final IndexService service;

    @PostMapping("/forgot")
    public Result<Void> forgot(@Validated @RequestBody ForgotRo ro) {
        service.forgot(ro);
        return Result.success();
    }

    @PostMapping("/register")
    public Result<Void> register(@Validated @RequestBody RegisterRo ro) {
        service.register(ro);
        return Result.success();
    }
}
