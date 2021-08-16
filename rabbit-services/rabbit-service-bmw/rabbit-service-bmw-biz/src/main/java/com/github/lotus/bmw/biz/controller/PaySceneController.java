package com.github.lotus.bmw.biz.controller;

import com.github.lotus.bmw.biz.pojo.vo.PaySceneItemVo;
import com.github.lotus.bmw.biz.service.PaySceneService;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hocgin on 2021/8/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequestMapping("/scene")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaySceneController {
    private final PaySceneService service;

    @ApiOperation("获取支付场景配置")
    @GetMapping("/config")
    public Result<List<PaySceneItemVo>> listBySceneCode(@RequestParam("sceneCode") String sceneCode, @RequestParam("accessMchId") Long accessMchId) {
        return Result.success(service.listBySceneCodeAndAccessMchId(sceneCode, accessMchId));
    }
}
