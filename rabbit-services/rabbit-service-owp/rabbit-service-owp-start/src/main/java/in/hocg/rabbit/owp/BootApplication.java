package in.hocg.rabbit.owp;

import in.hocg.boot.mybatis.plus.extensions.context.constants.GlobalConstants;
import in.hocg.rabbit.common.constant.GlobalConstant;
import in.hocg.rabbit.owp.api.OwpServiceName;
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
@SpringBootApplication(scanBasePackages = {OwpServiceName.PACKAGE, GlobalConstants.PACKAGE_PREFIX})
@MapperScan(annotationClass = Mapper.class, value = {OwpServiceName.PACKAGE, GlobalConstants.PACKAGE_PREFIX})
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@EnableFeignClients(basePackages = GlobalConstant.DEFAULT_FEIGN_BASE_PACKAGE)
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
