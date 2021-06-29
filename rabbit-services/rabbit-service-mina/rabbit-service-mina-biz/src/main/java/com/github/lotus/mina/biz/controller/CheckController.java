package com.github.lotus.mina.biz.controller;

import com.github.lotus.mina.biz.service.CheckService;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2021/1/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = "mina::通用")
@RestController
@RequestMapping("/{appid}")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CheckController {
    private final CheckService service;

    @ApiOperation("文字检查")
    @PostMapping("/check-message")
    public Result<Boolean> checkMessage(@PathVariable String appid,
                                        @RequestParam("text") String text) {
        return Result.success(service.checkMessage(appid, text));
    }

    @ApiOperation("图片检查")
    @PostMapping("/check-image")
    public Result<Boolean> checkImage(@PathVariable String appid,
                                      @RequestParam("imageUrl") String imageUrl) {
        return Result.success(service.checkImage(appid, imageUrl));
    }
}
