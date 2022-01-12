package in.hocg.rabbit.ws.biz.basic;

import in.hocg.boot.utils.context.security.UserPrincipal;
import in.hocg.boot.ws.autoconfiguration.core.service.WebSocketUserService;
import in.hocg.rabbit.common.utils.JwtUtils;
import in.hocg.rabbit.ums.api.UserServiceApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * Created by hocgin on 2022/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WebSocketUserServiceImpl implements WebSocketUserService {

    @Override
    public Principal loadUserByTicket(String ticket) {
        try {
            return new UserPrincipal(JwtUtils.decode(ticket));
        } catch (Exception e) {
            log.warn("解析 ticket 失败", e);
            return new UserPrincipal(ticket);
        }
    }
}
