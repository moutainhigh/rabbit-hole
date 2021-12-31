package in.hocg.rabbit.chaos;

import in.hocg.rabbit.common.constant.GlobalConstant;
import in.hocg.rabbit.mall.api.MallServiceName;
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
    "in.hocg.rabbit.chaos", "in.hocg.rabbit.wl",
    "in.hocg.rabbit.bmw",
    "in.hocg.rabbit.docking", "in.hocg.rabbit.ums",
    "in.hocg.rabbit.mina", "in.hocg.rabbit.com", MallServiceName.PACKAGE})
@MapperScan(value = {
    "in.hocg.rabbit.chaos", "in.hocg.rabbit.wl",
    "in.hocg.rabbit.bmw",
    "in.hocg.rabbit.docking", "in.hocg.rabbit.ums",
    "in.hocg.rabbit.mina", "in.hocg.rabbit.com", MallServiceName.PACKAGE}
    , annotationClass = Mapper.class)
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@EnableFeignClients(basePackages = GlobalConstant.DEFAULT_FEIGN_BASE_PACKAGE)
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
