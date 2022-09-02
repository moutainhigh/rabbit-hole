package in.hocg.rabbit.common.validation;


import in.hocg.rabbit.common.constant.RegexpConstant;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author admin
 */
@Pattern(regexp = RegexpConstant.VERIFY_CODE, message = "请输入4位验证码")
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyCode {
    String message() default "error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
