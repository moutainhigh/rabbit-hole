package in.hocg.rabbit.mina.biz.controller.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.pojo.ro.JoinRoomRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaGameCreateRoomRo;
import in.hocg.rabbit.mina.biz.pojo.ro.RoomPagingRo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameRoomComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameRoomOrdinaryVo;
import in.hocg.rabbit.mina.biz.service.GameRoomService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
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
        ro.setUserId(UserContextHolder.getUserId().orElse(null));
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
        ro.setUserId(UserContextHolder.getUserId().orElse(null));
        service.joinUser(ro);
        return Result.success();
    }
}

