package com.github.lotus.chaos.biz.controller;

import com.github.lotus.chaos.biz.pojo.vo.ProxyChannelInfoVo;
import com.github.lotus.chaos.biz.service.ProxyService;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2021/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RestController
@Api(tags = "chaos::代理服务")
@RequestMapping("/proxy")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProxyController {
    private final ProxyService service;

    @GetMapping("/{channelId}")
    public Result<ProxyChannelInfoVo> getChannelInfo(@PathVariable("channelId") String channelId) {
        return Result.success(service.getChannelInfo(channelId));
    }

}
