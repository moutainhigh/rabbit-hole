package com.github.lotus.mina.biz.controller;

import cn.hutool.core.util.StrUtil;
import com.github.lotus.mina.biz.pojo.ro.UploadYouTubeVideoRo;
import com.github.lotus.mina.biz.support.ytb.YouTubeService;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import in.hocg.boot.web.autoconfiguration.utils.web.ResponseUtils;
import in.hocg.boot.web.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/youtube")
public class YouTubeController {
    private final YouTubeService service;

    @ResponseBody
    @GetMapping("/authorize")
    public ResponseEntity<Void> authorize(@RequestParam("clientId") String clientId, @RequestParam(value = "scopes", required = false, defaultValue = "https://www.googleapis.com/auth/youtube") List<String> scopes) {
        String redirectUri = StrUtil.format("{}/mina/youtube/{}/callback", SpringContext.getBootConfig().getHostname(), clientId);
        return ResponseUtils.found(service.authorize(clientId, scopes, redirectUri));
    }

    @ResponseBody
    @GetMapping("/{clientId}/callback")
    public Result<String> callback(@PathVariable String clientId, @RequestParam("code") String code, @RequestParam("scope") List<String> scopes) {
        String redirectUri = StrUtil.format("{}/mina/youtube/{}/callback", SpringContext.getBootConfig().getHostname(), clientId);
        return Result.success(service.getCredential(clientId, redirectUri, scopes, code));
    }

    @ResponseBody
    @PostMapping("/upload")
    public Result<String> upload(@RequestBody UploadYouTubeVideoRo ro) {
        return Result.success(service.upload(ro));
    }
}
