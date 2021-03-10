package com.github.lotus.mina.biz.controller;

import com.github.lotus.mina.biz.pojo.ro.JoinRoomRo;
import com.github.lotus.mina.biz.pojo.ro.MinaGameCardPagingRo;
import com.github.lotus.mina.biz.pojo.ro.MinaGameCreateRoomRo;
import com.github.lotus.mina.biz.pojo.vo.GameRoomComplexVo;
import com.github.lotus.mina.biz.pojo.vo.MinaGameCardComplexVo;
import com.github.lotus.mina.biz.service.GameCardService;
import com.github.lotus.mina.biz.service.GameRoomService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
 * Created by hocgin on 2021/1/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = "mina::小程序游戏")
@RestController
@RequestMapping("/{appid}/game")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MinaGameController {
    private final GameCardService service;
    private final GameRoomService gameRoomService;

    @ApiOperation("游戏 - 分页查询")
    @PostMapping("/_paging")
    public Result<List<MinaGameCardComplexVo>> paging(@PathVariable String appid,
                                                      @Validated @RequestBody MinaGameCardPagingRo ro) {
        return Result.success(service.pagingForMina(ro).getRecords());
    }

    @ApiOperation("创建房间")
    @PostMapping("/room")
    public Result<GameRoomComplexVo> createRoom(@PathVariable String appid,
                                                @Validated @RequestBody MinaGameCreateRoomRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(gameRoomService.createAndGet(ro));
    }

    @ApiOperation("获取房间")
    @GetMapping("/room/{encoding}")
    public Result<GameRoomComplexVo> getRoomByEncoding(@PathVariable String appid,
                                                       @PathVariable("encoding") String encoding) {
        return Result.success(gameRoomService.getComplexByEncoding(encoding));
    }

    @ApiOperation("加入房间")
    @PostMapping("/room/{encoding}/join")
    public Result<Void> joinRoom(@PathVariable String appid,
                                 @Validated @RequestBody JoinRoomRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        gameRoomService.joinUser(ro);
        return Result.success();
    }
}
