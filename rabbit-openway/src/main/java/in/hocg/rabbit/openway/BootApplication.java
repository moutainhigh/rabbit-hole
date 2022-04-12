package in.hocg.rabbit.openway;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import in.hocg.rabbit.common.constant.GlobalConstant;
import in.hocg.rabbit.openway.basic.route.GatewayRouteLocator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2022/4/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@EnableAsync
@RestController
@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@EnableFeignClients(basePackages = GlobalConstant.DEFAULT_FEIGN_BASE_PACKAGE)
public class BootApplication {
    private final GatewayRouteLocator locator;

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @GetMapping("/refresh")
    public Result<Void> refresh() {
        locator.reloadRoutes();
        return Result.success();
    }
}
