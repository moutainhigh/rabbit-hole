package in.hocg.rabbit.openway;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.common.constant.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2022/4/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RestController
@SpringBootApplication
@EnableFeignClients(basePackages = GlobalConstant.DEFAULT_FEIGN_BASE_PACKAGE)
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @RequestMapping("/E12453")
    public Result<String> hello(@RequestBody String body) {
        log.info("body: {}", body);
        return Result.success("hello");
    }
}
