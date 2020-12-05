package com.github.lotus.sba;

import com.github.lotus.common.constant.GlobalConstant;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by hocgin on 2020/12/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@EnableAdminServer
@SpringBootApplication
@EnableFeignClients(basePackages = GlobalConstant.DEFAULT_FEIGN_BASE_PACKAGE)
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
