package in.hocg.rabbit.cv.api.named;


import in.hocg.boot.named.annotation.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hocgin on 2021/12/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Named(useService = CvNamedServiceApi.class)
public @interface CvNamed {

    String idFor();

    String type() default "";

    String[] args() default {};

    boolean useCache() default true;
}
