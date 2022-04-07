package in.hocg.rabbit.chaos;

import in.hocg.boot.mybatis.plus.extensions.context.constants.GlobalConstants;
import in.hocg.rabbit.common.constant.GlobalConstant;
import in.hocg.rabbit.cv.api.CvServiceName;
import in.hocg.rabbit.mall.api.MallServiceName;
import in.hocg.rabbit.rcm.api.RcmServiceName;
import in.hocg.rabbit.ws.api.WsServiceName;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by hocgin on 2020/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@EnableRetry
@EnableScheduling
@SpringBootApplication(scanBasePackages = {
    "in.hocg.rabbit.chaos", "in.hocg.rabbit.wl",
    "in.hocg.rabbit.bmw",
    "in.hocg.rabbit.docking", "in.hocg.rabbit.ums",
    "in.hocg.rabbit.mina", "in.hocg.rabbit.com",
    CvServiceName.PACKAGE, RcmServiceName.PACKAGE,
    WsServiceName.PACKAGE, MallServiceName.PACKAGE})
@MapperScan(annotationClass = Mapper.class, value = {
    "in.hocg.rabbit.chaos", "in.hocg.rabbit.wl",
    "in.hocg.rabbit.bmw",
    "in.hocg.rabbit.docking", "in.hocg.rabbit.ums",
    "in.hocg.rabbit.mina", "in.hocg.rabbit.com",
    CvServiceName.PACKAGE, RcmServiceName.PACKAGE,
    WsServiceName.PACKAGE, MallServiceName.PACKAGE,
    GlobalConstants.PACKAGE_PREFIX})
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@EnableFeignClients(basePackages = GlobalConstant.DEFAULT_FEIGN_BASE_PACKAGE)
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
