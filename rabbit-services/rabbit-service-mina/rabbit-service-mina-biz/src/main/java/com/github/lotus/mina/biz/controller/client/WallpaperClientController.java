package com.github.lotus.mina.biz.controller.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mina.biz.pojo.ro.MinaMobileWallpaperCompleteRo;
import com.github.lotus.mina.biz.pojo.ro.MinaMobileWallpaperPagingRo;
import com.github.lotus.mina.biz.pojo.ro.MinaMobileWallpaperTagsPagingRo;
import com.github.lotus.mina.biz.pojo.vo.MinaMobileWallpaperComplexVo;
import com.github.lotus.mina.biz.service.MobileWallpaperService;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [模块] 手机壁纸表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-03-13
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping({"/{appid}/mobile-wallpaper", "/wallpaper"})
public class WallpaperClientController {
    private final MobileWallpaperService service;

    @ApiOperation("手机壁纸 - 分页查询")
    @PostMapping("/_paging")
    public Result<IPage<MinaMobileWallpaperComplexVo>> paging(@PathVariable(required = false) String appid,
                                                              @Validated @RequestBody MinaMobileWallpaperPagingRo ro) {
        return Result.success(service.paging(ro));
    }


    @ApiOperation("手机壁纸 - 分页查询")
    @PostMapping("/random/_paging")
    public Result<IPage<MinaMobileWallpaperComplexVo>> randomComplete(@PathVariable(required = false) String appid,
                                                                      @Validated @RequestBody MinaMobileWallpaperCompleteRo ro) {
        return Result.success(service.randomComplete(ro));
    }

    @ApiOperation("手机壁纸标签 - 分页查询")
    @PostMapping("/tags/_paging")
    public Result<IPage<String>> paging(@PathVariable(required = false) String appid,
                                        @Validated @RequestBody MinaMobileWallpaperTagsPagingRo ro) {
        return Result.success(service.pagingByTags(ro));
    }
}

