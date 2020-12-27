package com.github.lotus.chaos.biz.modules.lang.controller;

import com.github.lotus.chaos.biz.modules.lang.pojo.ro.WallpaperCompleteRo;
import com.github.lotus.chaos.biz.modules.lang.pojo.ro.WallpaperPagingRo;
import com.github.lotus.chaos.biz.modules.lang.pojo.vo.WallpaperComplexVo;
import com.github.lotus.chaos.biz.modules.lang.service.WallpaperService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Api(tags = "chaos::壁纸")
@RestController
@RequestMapping("/wallpaper")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WallpaperController {
    private final WallpaperService service;

    @UseLogger("分页查询 - 壁纸")
    @ApiOperation("分页查询 - 壁纸")
    @PostMapping("/_paging")
    public Result<List<WallpaperComplexVo>> paging(@Validated @RequestBody WallpaperPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @UseLogger("搜索 - 壁纸")
    @ApiOperation("搜索 - 壁纸")
    @PostMapping("/_complete")
    public Result<List<WallpaperComplexVo>> complete(@Validated @RequestBody WallpaperCompleteRo ro) {
        return Result.success(service.complete(ro));
    }
}
