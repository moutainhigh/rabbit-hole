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

@Pattern(regexp = RegexpConstant.PHONE, message = "请输入正确的手机号码")
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
