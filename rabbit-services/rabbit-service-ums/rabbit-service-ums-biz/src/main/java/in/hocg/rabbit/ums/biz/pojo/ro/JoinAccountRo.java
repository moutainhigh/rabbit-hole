package in.hocg.rabbit.ums.biz.pojo.ro;

import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.validation.annotation.EnumRange;
import in.hocg.rabbit.common.validation.Username;
import in.hocg.rabbit.common.validation.VerifyCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2020/12/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@Setter
@ApiModel(description = "注册")
public class JoinAccountRo {
    @NotNull(message = "该注册方式暂不支持")
    @EnumRange(enumClass = Mode.class, message = "该注册方式暂不支持")
    @ApiModelProperty(value = "模式", required = true, example = "use_phone")
    private String mode = Mode.UsePhone.getCode();

    @ApiModelProperty(value = "仅邮件模式使用")
    private Mode.UseEmailRo emailMode;

    @ApiModelProperty(value = "仅手机号模式使用")
    private Mode.UsePhoneRo phoneMode;

    @ApiModelProperty(value = "仅账户模式使用")
    private Mode.UseUsernameRo usernameMode;

    @Getter
    @RequiredArgsConstructor
    public enum Mode implements ICode {
        UseUsername("use_username"),
        UsePhone("use_phone"),
        UseEmail("use_email");
        private final String code;

        @Getter
        @Setter
        @ApiModel(description = "账户模式")
        public static class UseUsernameRo {
            @Username
            @NotBlank(message = "账户不能为空")
            @ApiModelProperty(value = "指定用户名")
            private String username;
            @NotBlank(message = "密码不能为空")
            @ApiModelProperty(value = "指定密码")
            private String password;
        }

        @Getter
        @Setter
        @ApiModel(description = "邮件模式")
        public static class UseEmailRo {
            @NotBlank(message = "验证码序列号不能为空")
            @ApiModelProperty("验证码序列号")
            private String serialNo;
            @NotBlank(message = "密码不能为空")
            @ApiModelProperty(value = "指定密码")
            private String password;
            @VerifyCode
            @NotBlank(message = "验证码不能为空")
            @ApiModelProperty(value = "验证码")
            private String verifyCode;
        }

        @Getter
        @Setter
        @ApiModel(description = "手机号模式")
        public static class UsePhoneRo {
            @NotBlank(message = "验证码序列号不能为空")
            @ApiModelProperty("验证码序列号")
            private String serialNo;
            @VerifyCode
            @NotBlank(message = "验证码不能为空")
            @ApiModelProperty(value = "验证码")
            private String verifyCode;
        }
    }
}
