package in.hocg.rabbit.mina.biz.controller.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaMobileWallpaperCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaMobileWallpaperPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaMobileWallpaperTagsPagingRo;
import in.hocg.rabbit.mina.biz.pojo.vo.MinaMobileWallpaperComplexVo;
import in.hocg.rabbit.mina.biz.service.MobileWallpaperService;
import in.hocg.boot.utils.struct.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
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
@Api(tags = "mina::手机壁纸")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping({"/mobile-wallpaper"})
public class WallpaperClientController {
    private final MobileWallpaperService service;

    @ApiOperation("手机壁纸 - 分页查询")
    @PostMapping("/_paging")
    public Result<IPage<MinaMobileWallpaperComplexVo>> paging(@Validated @RequestBody MinaMobileWallpaperPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @ApiOperation("手机壁纸 - 分页查询")
    @PostMapping("/random/_paging")
    public Result<IPage<MinaMobileWallpaperComplexVo>> randomComplete(@Validated @RequestBody MinaMobileWallpaperCompleteRo ro) {
        return Result.success(service.randomComplete(ro));
    }

    @ApiOperation("手机壁纸标签 - 分页查询")
    @PostMapping("/tags/_paging")
    public Result<IPage<String>> paging(@Validated @RequestBody MinaMobileWallpaperTagsPagingRo ro) {
        return Result.success(service.pagingByTags(ro));
    }
}

