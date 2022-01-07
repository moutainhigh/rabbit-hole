package in.hocg.rabbit.mina.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.pojo.ro.GameCardCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.GameCardPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.GameCardSaveRo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameCardComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameCardOrdinaryVo;
import in.hocg.rabbit.mina.biz.service.GameCardService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.validation.group.Insert;
import in.hocg.boot.validation.group.Update;
import in.hocg.boot.utils.struct.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2021/2/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = "mina::小程序游戏")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/game-card")
public class GameCardController {
    private final GameCardService service;

    @ApiOperation("分页查询 - 小程序游戏")
    @PostMapping("/_paging")
    public Result<IPage<GameCardOrdinaryVo>> paging(@Validated @RequestBody GameCardPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @ApiOperation("检索 - 小程序游戏")
    @UseLogger("检索 - 小程序游戏")
    @PostMapping("/_complete")
    public Result<List<GameCardOrdinaryVo>> complete(@Validated @RequestBody GameCardCompleteRo ro) {
        return Result.success(service.complete(ro));
    }

    @ApiOperation("新增 - 小程序游戏")
    @PostMapping
    public Result<Void> insertOne(@Validated({Insert.class}) @RequestBody GameCardSaveRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.insertOne(ro);
        return Result.success();
    }

    @ApiOperation("修改 - 小程序游戏")
    @PutMapping("/{id}")
    public Result<Void> updateOne(@ApiParam(value = "小程序游戏", required = true) @PathVariable Long id,
                                  @Validated({Update.class}) @RequestBody GameCardSaveRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.updateOne(id, ro);
        return Result.success();
    }

    @ApiOperation("查询详情 - 小程序游戏")
    @GetMapping("/{id}")
    public Result<GameCardComplexVo> getComplex(@ApiParam(value = "小程序游戏", required = true) @PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }

    @ApiOperation("删除 - 小程序游戏")
    @DeleteMapping("/{id}")
    public Result<Void> deleteOne(@ApiParam(value = "小程序游戏", required = true) @PathVariable Long id) {
        service.deleteOne(id);
        return Result.success();
    }

}
