package in.hocg.rabbit.ums.api.pojo.ro;

import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.validation.annotation.EnumRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/12/28
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class ForgotRo {
    @NotNull(message = "方式暂不支持")
    @EnumRange(message = "方式暂不支持", enumClass = ForgotRo.Mode.class)
    @ApiModelProperty(value = "找回方式", required = true)
    private String mode;
    @ApiModelProperty("手机模式")
    private PhoneMode phoneMode;
    @ApiModelProperty("邮件模式")
    private EmailMode emailMode;

    @Data
    public static class PhoneMode {
        @NotBlank(message = "新密码错误")
        @ApiModelProperty("新密码")
        private String password;
        @NotBlank(message = "手机号码错误")
        @ApiModelProperty("手机号")
        private String phone;
        @NotBlank(message = "验证码错误")
        @ApiModelProperty("验证码")
        private String code;
    }

    @Data
    public static class EmailMode {
        @Email(message = "邮箱地址错误")
        @ApiModelProperty("邮箱地址")
        private String email;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Mode implements ICode {
        UsePhone("use_phone"),
        UseEmail("use_email");
        private final String code;
    }
}
