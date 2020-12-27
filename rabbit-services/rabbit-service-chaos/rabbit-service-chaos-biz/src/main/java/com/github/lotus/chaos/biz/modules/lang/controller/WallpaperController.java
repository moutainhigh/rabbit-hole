package com.github.lotus.chaos.biz.modules.lang.controller;

import com.github.lotus.chaos.biz.modules.lang.pojo.ro.WallpaperPagingRo;
import com.github.lotus.chaos.biz.modules.lang.pojo.vo.WallpaperComplexVo;
import com.github.lotus.chaos.biz.modules.lang.service.WallpaperService;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Api(tags = "chaos::壁纸")
@Controller
@RequestMapping("/wallpaper")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WallpaperController {
    private final WallpaperService service;

    public Result<List<WallpaperComplexVo>> paging(@Validated @RequestBody WallpaperPagingRo ro) {
        return Result.success(service.paging(ro));
    }
}
