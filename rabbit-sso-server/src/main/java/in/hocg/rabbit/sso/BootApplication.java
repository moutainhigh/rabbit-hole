package in.hocg.rabbit.sso;

import in.hocg.rabbit.common.constant.GlobalConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by hocgin on 2020/8/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@EnableFeignClients(basePackages = GlobalConstant.DEFAULT_FEIGN_BASE_PACKAGE)
@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @RequestMapping("/user")
    @ResponseBody
    public Principal me(Principal principal) {
        return principal;
    }
}
