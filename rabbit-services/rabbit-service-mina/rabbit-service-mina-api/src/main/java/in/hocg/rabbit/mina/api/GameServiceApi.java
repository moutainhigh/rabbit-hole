package in.hocg.rabbit.mina.api;

import in.hocg.rabbit.mina.api.pojo.ro.UploadYouTubeRo;
import in.hocg.rabbit.mina.api.pojo.vo.GameRoomVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = MinaServiceName.NAME)
public interface GameServiceApi {

    String CONTEXT_ID = "GameServiceApi";

    @PostMapping(value = CONTEXT_ID + "/getRoom", headers = MinaServiceName.FEIGN_HEADER)
    GameRoomVo getRoom(@RequestParam String roomCode);
}
