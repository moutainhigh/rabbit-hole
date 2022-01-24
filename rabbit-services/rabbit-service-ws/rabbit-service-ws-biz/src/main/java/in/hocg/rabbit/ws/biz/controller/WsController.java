package in.hocg.rabbit.ws.biz.controller;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import in.hocg.rabbit.ws.biz.service.WsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2022/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class WsController {
    private final WsService wsService;

    @GetMapping("/ticket")
    public Result<String> getTicket() {
        Long userId = UserContextHolder.getUserIdThrow();
        return Result.success(wsService.getTicket(userId));
    }
}
