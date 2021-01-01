package com.github.lotus.chaos;

import com.github.lotus.common.constant.GlobalConstant;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by hocgin on 2020/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@SpringBootApplication(scanBasePackages = {
    "com.github.lotus.chaos", "com.github.lotus.wl", "com.github.lotus.docking", "com.github.lotus.ums", "com.github.lotus.mina"})
@MapperScan(value = {
    "com.github.lotus.chaos", "com.github.lotus.wl", "com.github.lotus.docking", "com.github.lotus.ums", "com.github.lotus.mina"}
    , annotationClass = Mapper.class)
@EnableFeignClients(basePackages = GlobalConstant.DEFAULT_FEIGN_BASE_PACKAGE)
public class ChaosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaosApplication.class, args);
    }

}
