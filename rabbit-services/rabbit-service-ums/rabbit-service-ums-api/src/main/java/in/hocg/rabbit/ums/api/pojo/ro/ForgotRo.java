package in.hocg.rabbit.ums.api.pojo.ro;

import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.validation.annotation.EnumRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/12/28
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@Setter
@ApiModel(description = "忘记密码")
public class ForgotRo {
    @NotNull(message = "方式暂不支持")
    @EnumRange(message = "方式暂不支持", enumClass = ForgotRo.Mode.class)
    @ApiModelProperty(value = "找回方式", required = true)
    private String mode;
    @ApiModelProperty("手机模式")
    private Mode.UsePhoneRo phoneMode;
    @ApiModelProperty("邮件模式")
    private Mode.UseEmailRo emailMode;

    @Getter
    @RequiredArgsConstructor
    public enum Mode implements ICode {
        UsePhone("use_phone"),
        UseEmail("use_email");
        private final String code;

        @Getter
        @Setter
        @ApiModel(description = "短信模式")
        public static class UsePhoneRo {
            @NotBlank(message = "短信序列号不能为空")
            @ApiModelProperty("短信序列号")
            private String serialNo;
            @NotBlank(message = "验证码错误")
            @ApiModelProperty("验证码")
            private String verifyCode;

            @NotBlank(message = "新密码错误")
            @ApiModelProperty("新密码")
            private String password;
        }

        @Getter
        @Setter
        @ApiModel(description = "邮箱模式")
        public static class UseEmailRo {
            @NotBlank(message = "短信序列号不能为空")
            @ApiModelProperty("短信序列号")
            private String serialNo;
            @NotBlank(message = "验证码错误")
            @ApiModelProperty("验证码")
            private String verifyCode;

            @NotBlank(message = "新密码错误")
            @ApiModelProperty("新密码")
            private String password;
        }
    }
}
