package in.hocg.rabbit.ws.biz.controller;

import in.hocg.rabbit.ws.biz.service.NesRoomService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * Created by hocgin on 2022/6/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Controller
@MessageMapping("/game/nes")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class NesController {
    private final NesRoomService roomService;

    @MessageMapping
    public void onMessage(@DestinationVariable String body) {
        log.info("收到消息：{}", body);
    }

}
