package in.hocg.rabbit.openway.basic.handle;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.file.AccessDeniedException;

/**
 * Created by hocgin on 2022/4/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface ExceptionHandler {
    Mono<Void> handle(ServerWebExchange exchange, Exception e);
}
