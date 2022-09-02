package in.hocg.rabbit.chaos.api.pojo.ro;

import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.validation.annotation.EnumRange;
import in.hocg.rabbit.chaos.api.enums.VerifyCodeOptType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author hocgin
 */
@Getter
@Setter
@ApiModel(description = "发送验证码")
@Accessors(chain = true)
public class SendVerifyCodeRo {
    @NotNull(message = "该模式暂不支持")
    @EnumRange(message = "该模式暂不支持", enumClass = Mode.class)
    @ApiModelProperty(value = "模式", required = true)
    private String mode = Mode.UseSms.getCode();

    @ApiModelProperty(value = "仅手机模式使用")
    private Mode.UseSmsRo smsMode;

    @ApiModelProperty(value = "仅邮件模式使用")
    private Mode.UseEmailRo emailMode;

    @NotNull(message = "操作类型错误")
    @EnumRange(message = "操作类型错误", enumClass = VerifyCodeOptType.class)
    @ApiModelProperty("操作类型")
    private String optType = VerifyCodeOptType.Other.getCodeStr();
    @ApiModelProperty("操作IP")
    private String optIp;
    @Min(value = 1, message = "有效时长错误")
    @NotNull(message = "有效时长错误")
    @ApiModelProperty("有效时长(单位:分钟)")
    private Long duration = 5L;

    @Getter
    @RequiredArgsConstructor
    public enum Mode implements ICode {
        UseSms("use_sms"),
        UseEmail("use_email");
        private final String code;

        @Getter
        @Setter
        @ApiModel(description = "手机号模式")
        public class UseSmsRo {
            @NotNull(message = "手机号不能为空")
            @NotEmpty(message = "手机号不能为空")
            private String phone;
        }

        @Getter
        @Setter
        @ApiModel(description = "邮箱模式")
        public class UseEmailRo {
            @NotNull(message = "邮箱不能为空")
            @NotEmpty(message = "邮箱不能为空")
            @Email(message = "邮箱格式错误")
            private String email;
        }
    }
}
