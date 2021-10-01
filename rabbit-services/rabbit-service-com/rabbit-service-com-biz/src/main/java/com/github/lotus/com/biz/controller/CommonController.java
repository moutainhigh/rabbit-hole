package com.github.lotus.com.biz.controller;

import com.github.lotus.com.biz.service.ComService;
import in.hocg.boot.web.autoconfiguration.utils.web.ResponseUtils;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2021/10/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = "com::通用")
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/")
public class CommonController {
    private final ComService service;

    @GetMapping("/favicon")
    public ResponseEntity<?> favicon(@RequestParam("url") String url, @RequestParam(value = "defUrl", required = false) String defUrl) {
        return ResponseUtils.preview(service.getFavicon(url, defUrl));
    }

}
