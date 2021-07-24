package com.github.lotus.mina.biz.controller;

import com.github.lotus.mina.biz.service.CheckService;
import com.github.lotus.usercontext.basic.HeaderConstants;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2021/1/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = "mina::通用")
@RestController
@RequestMapping({"/{appid}", "/common"})
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CheckController {
    private final CheckService service;

    @ApiOperation("文字检查")
    @PostMapping("/check-message")
    public Result<Boolean> checkMessage(@PathVariable(required = false) String appid,
                                        @RequestHeader(name = HeaderConstants.SOURCE) String happid,
                                        @RequestParam("text") String text) {
        appid = LangUtils.getOrDefault(appid, happid);
        return Result.success(service.checkMessage(appid, text));
    }

    @ApiOperation("图片检查")
    @PostMapping("/check-image")
    public Result<Boolean> checkImage(@PathVariable(required = false) String appid,
                                      @RequestHeader(name = HeaderConstants.SOURCE) String happid,
                                      @RequestParam("imageUrl") String imageUrl) {
        appid = LangUtils.getOrDefault(appid, happid);
        return Result.success(service.checkImage(appid, imageUrl));
    }
}
