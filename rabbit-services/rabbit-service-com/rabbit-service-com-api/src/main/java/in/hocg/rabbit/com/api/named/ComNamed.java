package in.hocg.rabbit.com.api.named;

import in.hocg.boot.named.annotation.Named;
import in.hocg.rabbit.chaos.api.named.ChaosNamedServiceApi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hocgin on 2021/12/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Named(useService = ComNamedServiceApi.class)
public @interface ComNamed {
    String idFor();

    String type() default "";

    String[] args() default {};

    boolean useCache() default true;
}
