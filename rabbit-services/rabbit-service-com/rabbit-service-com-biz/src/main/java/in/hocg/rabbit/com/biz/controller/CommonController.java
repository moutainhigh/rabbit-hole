package in.hocg.rabbit.com.biz.controller;

import in.hocg.rabbit.com.biz.manager.ComService;
import in.hocg.boot.web.autoconfiguration.utils.web.ResponseUtils;
import in.hocg.boot.utils.struct.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @ApiOperation("网站图标 图片")
    @GetMapping("/favicon")
    public ResponseEntity<?> favicon(@RequestParam("url") String url, @RequestParam(value = "defUrl", required = false) String defUrl) {
        return ResponseUtils.preview(service.getFavicon(url, defUrl));
    }

    @ApiOperation("网站图标 URL")
    @GetMapping("/favicon-url")
    public Result<String> faviconUrl(@RequestParam("url") String url, @RequestParam(value = "defUrl", required = false) String defUrl) {
        return Result.success(service.getFaviconUrl(url, defUrl));
    }
}
