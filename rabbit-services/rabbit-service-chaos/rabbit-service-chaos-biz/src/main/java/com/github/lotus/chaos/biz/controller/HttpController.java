package com.github.lotus.chaos.biz.controller;

import cn.hutool.core.date.DateUtil;
import com.github.lotus.chaos.biz.pojo.ro.WallpaperCompleteRo;
import com.github.lotus.chaos.biz.pojo.ro.WallpaperPagingRo;
import com.github.lotus.chaos.biz.pojo.ro.WallpaperTopicPagingRo;
import com.github.lotus.chaos.biz.pojo.vo.WallpaperComplexVo;
import com.github.lotus.chaos.biz.pojo.vo.WallpaperTopicVo;
import com.github.lotus.chaos.biz.support.http.HttpService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class HttpController {
    private final HttpService service;

    @UseLogger("获取主题 - 壁纸")
    @ApiOperation("获取主题 - 壁纸")
    @GetMapping("/wallpaper/topic")
    public Result<List<WallpaperTopicVo>> topic() {
        return Result.success(service.getTopic());
    }

    @UseLogger("随机获取 - 壁纸")
    @ApiOperation("随机获取 - 壁纸")
    @GetMapping("/wallpaper/random")
    public Result<WallpaperComplexVo> random() {
        return Result.success(service.random());
    }

    @UseLogger("查询主题 - 壁纸")
    @ApiOperation("查询主题 - 壁纸")
    @PostMapping("/wallpaper/topic/{topicId}/_paging")
    public Result<List<WallpaperComplexVo>> pagingByTopic(@PathVariable("topicId") String topicId,
                                                          @Validated @RequestBody WallpaperTopicPagingRo ro) {
        return Result.success(service.pagingByTopic(topicId, ro));
    }

    @UseLogger("分页查询 - 壁纸")
    @ApiOperation("分页查询 - 壁纸")
    @PostMapping("/wallpaper/_paging")
    public Result<List<WallpaperComplexVo>> pagingWallpaper(@Validated @RequestBody WallpaperPagingRo ro) {
        return Result.success(service.pagingWallpaper(ro));
    }

    @UseLogger("搜索 - 壁纸")
    @ApiOperation("搜索 - 壁纸")
    @PostMapping("/wallpaper/_complete")
    public Result<List<WallpaperComplexVo>> completeWallpaper(@Validated @RequestBody WallpaperCompleteRo ro) {
        return Result.success(service.completeWallpaper(ro));
    }

    @UseLogger("密令(不思议迷宫)")
    @ApiOperation("密令(不思议迷宫)")
    @GetMapping("/gumballs/gift")
    public Result<String> getGumballsGift() {
        return Result.success(service.getGumballsGift(DateUtil.today()));
    }
}
