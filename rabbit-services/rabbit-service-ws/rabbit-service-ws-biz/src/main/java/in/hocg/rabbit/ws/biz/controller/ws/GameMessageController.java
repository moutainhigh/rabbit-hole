package in.hocg.rabbit.ws.biz.controller.ws;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import in.hocg.boot.utils.exception.ServiceException;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mina.api.GameServiceApi;
import in.hocg.rabbit.mina.api.pojo.vo.GameRoomVo;
import in.hocg.rabbit.ws.biz.pojo.ro.SyncGameRo;
import in.hocg.rabbit.ws.biz.pojo.vo.SyncGameVo;
import in.hocg.rabbit.ws.biz.utils.ImageUtils;
import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by hocgin on 2022/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Controller
@MessageMapping("/game")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class GameMessageController {
    private final SimpMessagingTemplate smt;

    /**
     * 游戏指令和动画同步
     * - 1P -同步动画-> 2P
     * - 2P -同步操作指令-> 2P
     *
     * @param roomCode
     * @param principal
     * @return
     */
    @SneakyThrows
    @MessageMapping("/{roomCode}/sync")
    public void syncCmd(@DestinationVariable String roomCode, SyncGameRo ro) {
        SyncGameVo result = new SyncGameVo();
        Object cmd = ro.getCmd();
        String screen = ro.getScreen();
        if (Objects.nonNull(cmd)) {
            result.setCmd(cmd);
            smt.convertAndSend("/queue/game/" + roomCode + "/sync/p1", result);
        } else if (StrUtil.isNotBlank(screen)) {
            result.setScreen(screen);
            smt.convertAndSend("/queue/game/" + roomCode + "/sync/p2", result);
        }
    }


}
