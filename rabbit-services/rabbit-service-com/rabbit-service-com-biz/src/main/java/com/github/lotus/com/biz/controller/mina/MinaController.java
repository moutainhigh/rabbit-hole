package com.github.lotus.com.biz.controller.mina;

import com.github.lotus.com.biz.service.MinaService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.web.result.Result;
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
@RequestMapping("/mina")
public class MinaController {
    private final MinaService service;

    @ApiOperation("签到")
    @PostMapping("/sign")
    public Result<Void> signIn() {
        UserContextHolder.getUserId().ifPresent(service::userSign);
        return Result.success();
    }
}
