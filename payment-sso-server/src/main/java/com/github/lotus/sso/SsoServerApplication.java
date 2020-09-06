package com.github.lotus.sso;

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
@EnableFeignClients
@SpringBootApplication
public class SsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
    }

    @RequestMapping("/user")
    @ResponseBody
    public Principal me(Principal principal) {
        return principal;
    }
}
