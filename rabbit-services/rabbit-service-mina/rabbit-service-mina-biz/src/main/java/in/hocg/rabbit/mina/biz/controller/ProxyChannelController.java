package in.hocg.rabbit.mina.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.pojo.ro.DeleteRo;
import in.hocg.rabbit.mina.biz.pojo.vo.ProxyChannelInfoVo;
import in.hocg.rabbit.mina.biz.service.ProxyChannelService;
import in.hocg.rabbit.mina.biz.pojo.ro.ProxyChannelPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.ProxyChannelSaveRo;
import in.hocg.rabbit.mina.biz.pojo.vo.ProxyChannelComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.ProxyChannelOrdinaryVo;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.validation.group.Insert;
import in.hocg.boot.validation.group.Update;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2021/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RestController
@Api(tags = "chaos::代理服务")
@RequestMapping("/proxy")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProxyChannelController {
    private final ProxyChannelService service;

    @UseLogger
    @ApiOperation("获取详情 - 代理隧道")
    @GetMapping("/{channelId}")
    public Result<ProxyChannelInfoVo> getChannelInfo(@PathVariable("channelId") String channelId) {
        return Result.success(service.getChannelInfo(channelId));
    }

    @UseLogger
    @ApiOperation("分页查询 - 代理隧道")
    @PostMapping("/_paging")
    public Result<IPage<ProxyChannelOrdinaryVo>> paging(@Validated @RequestBody ProxyChannelPagingRo ro) {
        return Result.success(service.paging(ro));
    }


    @ApiOperation("新增 - 代理隧道")
    @PostMapping
    public Result<Void> insertOne(@Validated({Insert.class}) @RequestBody ProxyChannelSaveRo ro) {
        ro.setRequestUserId(UserContextHolder.getUserIdThrow());
        service.insertOne(ro);
        return Result.success();
    }

    @ApiOperation("修改 - 代理隧道")
    @PutMapping("/{id}")
    public Result<Void> updateOne(@ApiParam(value = "代理隧道", required = true) @PathVariable Long id,
                                  @Validated({Update.class}) @RequestBody ProxyChannelSaveRo ro) {
        ro.setRequestUserId(UserContextHolder.getUserIdThrow());
        service.updateOne(id, ro);
        return Result.success();
    }


    @ApiOperation("查询详情 - 代理隧道")
    @GetMapping("/{id}/complex")
    public Result<ProxyChannelComplexVo> getComplex(@ApiParam(value = "代理隧道", required = true) @PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }

    @ApiOperation("删除 - 代理隧道")
    @DeleteMapping
    public Result<Void> delete(@Validated @RequestBody DeleteRo ro) {
        service.removeByIds(ro.getId());
        return Result.success();
    }
}
