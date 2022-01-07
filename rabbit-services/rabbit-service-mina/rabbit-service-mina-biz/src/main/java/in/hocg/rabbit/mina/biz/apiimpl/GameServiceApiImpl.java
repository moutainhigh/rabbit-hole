package in.hocg.rabbit.mina.biz.apiimpl;

import in.hocg.rabbit.mina.api.GameServiceApi;
import in.hocg.rabbit.mina.api.pojo.vo.GameRoomVo;
import in.hocg.rabbit.mina.biz.service.GameRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2022/1/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class GameServiceApiImpl implements GameServiceApi {
    private final GameRoomService gameRoomService;

    @Override
    public GameRoomVo getRoom(String roomCode) {
        return gameRoomService.getRoomByRoomCode(roomCode);
    }
}
