package in.hocg.rabbit.com.biz.controller;


import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.boot.validation.group.Insert;
import in.hocg.boot.validation.group.Update;
import in.hocg.rabbit.com.biz.pojo.ro.UserAddressClientSaveRo;
import in.hocg.rabbit.com.biz.pojo.ro.UserAddressClientScrollRo;
import in.hocg.rabbit.com.biz.pojo.vo.UserAddressClientComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.UserAddressClientOrdinaryVo;
import in.hocg.rabbit.com.biz.service.UserAddressService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [用户模块] 物流地址表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/user-address")
public class UserAddressController {
    private final UserAddressService service;

    @UseLogger
    @PostMapping
    @ApiOperation("新增 - 用户地址")
    public Result<Void> insertOne(@Validated(Insert.class) @RequestBody UserAddressClientSaveRo ro) {
        ro.setOwnerUserId(UserContextHolder.getUserIdThrow());
        service.insertOne(ro);
        return Result.success();
    }

    @UseLogger
    @PutMapping("/{id}")
    @ApiOperation("更新 - 用户地址")
    public Result<Void> updateOne(@PathVariable("id") Long id, @Validated(Update.class) @RequestBody UserAddressClientSaveRo ro) {
        ro.setOwnerUserId(UserContextHolder.getUserIdThrow());
        service.updateOne(id, ro);
        return Result.success();
    }

    @UseLogger
    @GetMapping("/{id}/complex")
    @ApiOperation("查看 - 用户地址")
    public Result<UserAddressClientComplexVo> getComplexById(@PathVariable("id") Long id) {
        Long ownerUserId = UserContextHolder.getUserIdThrow();
        return Result.success(service.getComplex(id, ownerUserId));
    }

    @UseLogger
    @PostMapping("/_scroll")
    @ApiOperation("翻页 - 用户地址")
    public Result<IScroll<UserAddressClientOrdinaryVo>> scroll(@Validated @RequestBody UserAddressClientScrollRo ro) {
        ro.setOwnerUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.scroll(ro));
    }

    @UseLogger
    @DeleteMapping
    @ApiOperation("删除 - 用户地址")
    public Result<Void> delete(@Validated @RequestBody DeleteRo ro) {
        Long ownerUserId = UserContextHolder.getUserIdThrow();
        service.delete(ro, ownerUserId);
        return Result.success();
    }
}

