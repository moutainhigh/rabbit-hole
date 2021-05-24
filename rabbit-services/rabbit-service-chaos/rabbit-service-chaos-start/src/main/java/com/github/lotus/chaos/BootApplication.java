package com.github.lotus.chaos;

import com.github.lotus.common.constant.GlobalConstant;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by hocgin on 2020/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@SpringBootApplication(scanBasePackages = {
    "com.github.lotus.chaos", "com.github.lotus.wl",
    "com.github.lotus.pay",
    "com.github.lotus.docking", "com.github.lotus.ums",
    "com.github.lotus.mina", "com.github.lotus.com"})
@MapperScan(value = {
    "com.github.lotus.chaos", "com.github.lotus.wl",
    "com.github.lotus.pay",
    "com.github.lotus.docking", "com.github.lotus.ums",
    "com.github.lotus.mina", "com.github.lotus.com"}
    , annotationClass = Mapper.class)
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@EnableFeignClients(basePackages = GlobalConstant.DEFAULT_FEIGN_BASE_PACKAGE)
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
