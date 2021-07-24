package com.github.lotus.mina.biz.controller.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.common.constant.GlobalConstant;
import com.github.lotus.mina.biz.pojo.ro.JoinRoomRo;
import com.github.lotus.mina.biz.pojo.ro.MinaGameCreateRoomRo;
import com.github.lotus.mina.biz.pojo.ro.RoomPagingRo;
import com.github.lotus.mina.biz.pojo.vo.GameRoomComplexVo;
import com.github.lotus.mina.biz.pojo.vo.GameRoomOrdinaryVo;
import com.github.lotus.mina.biz.service.GameRoomService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.web.result.Result;
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

/**
 * <p>
 * [小程序模块] 游戏房间表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-03-08
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/game/room")
public class GameRoomClientController {
    private final GameRoomService service;

    @ApiOperation("分页查询")
    @PostMapping("/_paging")
    public Result<IPage<GameRoomOrdinaryVo>> paging(@Validated @RequestBody RoomPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @ApiOperation("创建房间")
    @PostMapping
    public Result<GameRoomComplexVo> createRoom(@Validated @RequestBody MinaGameCreateRoomRo ro) {
        ro.setUserId(UserContextHolder.getUserId().orElse(GlobalConstant.SUPPER_ADMIN_USER_ID));
        return Result.success(service.createAndGet(ro));
    }

    @ApiOperation("获取房间")
    @GetMapping("/{encoding}")
    public Result<GameRoomComplexVo> getRoomByEncoding(@PathVariable("encoding") String encoding) {
        return Result.success(service.getComplexByEncoding(encoding));
    }

    @ApiOperation("加入房间")
    @PostMapping("/join")
    public Result<Void> joinRoom(@Validated @RequestBody JoinRoomRo ro) {
        ro.setUserId(UserContextHolder.getUserId().orElse(GlobalConstant.SUPPER_ADMIN_USER_ID));
        service.joinUser(ro);
        return Result.success();
    }
}

