package in.hocg.rabbit.ws.biz.controller;

import cn.hutool.json.JSONUtil;
import in.hocg.rabbit.ws.biz.pojo.dto.MessageCmdDto;
import in.hocg.rabbit.ws.biz.pojo.ro.GameCmdRo;
import in.hocg.rabbit.ws.biz.pojo.ro.RoomSignalRo;
import in.hocg.rabbit.ws.biz.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

/**
 * Created by hocgin on 2022/6/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Controller
@MessageMapping
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class GameController {
    private final GameService service;

    @MessageMapping("/game")
    public void room(@RequestBody MessageCmdDto dto, Principal principal) {
        switch (dto.getName()) {
            case "room": {
                service.handleRoomRequest(JSONUtil.toBean(JSONUtil.toJsonStr(dto.getValue()), GameCmdRo.class));
                return;
            }
            case "room.signal": {
                service.handleRoomSignal(JSONUtil.toBean(JSONUtil.toJsonStr(dto.getValue()), RoomSignalRo.class), principal.getName());
                return;
            }
            default: {
                log.warn("未知指令: {}", dto.getName());
            }
        }
    }

}