package com.github.lotus.sso.pojo.ro;

import com.github.lotus.common.constant.RegexpConstant;
import in.hocg.boot.validation.autoconfigure.core.ICode;
import in.hocg.boot.validation.autoconfigure.core.annotation.EnumRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by hocgin on 2020/12/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "注册")
public class JoinAccountRo {
    @NotNull(groups = {EmailMode.class, PhoneMode.class, UsernameMode.class}, message = "该注册方式暂不支持")
    @EnumRange(groups = {EmailMode.class, PhoneMode.class, UsernameMode.class}, enumClass = Mode.class, message = "该注册方式暂不支持")
    @ApiModelProperty(value = "模式", required = true)
    private String mode = Mode.UsePhone.getCode();

    @Valid
    @NotNull(message = "邮件信息不能为空", groups = {EmailMode.class})
    @ApiModelProperty(value = "仅邮件模式使用")
    private EmailMode emailMode;

    @Valid
    @NotNull(message = "手机信息不能为空", groups = {PhoneMode.class})
    @ApiModelProperty(value = "仅手机号模式使用")
    private PhoneMode phoneMode;

    @Valid
    @NotNull(message = "账户信息不能为空", groups = {UsernameMode.class})
    @ApiModelProperty(value = "仅账户模式使用")
    private UsernameMode usernameMode;

    @Data
    @ApiModel(description = "账户模式")
    public static class UsernameMode {
        @Pattern(groups = {UsernameMode.class}, regexp = RegexpConstant.USERNAME, message = "账户仅支持6~12位的字母、数字或下划线")
        @NotBlank(groups = {UsernameMode.class}, message = "账户不能为空")
        @ApiModelProperty(value = "指定用户名(默认自动生成)")
        private String username;
        @Pattern(groups = {UsernameMode.class}, regexp = RegexpConstant.PASSWORD, message = "请输入6~32位的密码")
        @NotBlank(groups = {UsernameMode.class}, message = "密码不能为空")
        @ApiModelProperty(value = "指定密码(默认自动生成)")
        private String password;
    }

    @Data
    @ApiModel(description = "邮件模式")
    public static class EmailMode {
        @Email(groups = {EmailMode.class}, message = "请输入正确的邮件地址")
        @NotBlank(groups = {PhoneMode.class}, message = "邮件地址不能为空")
        @ApiModelProperty(value = "邮件地址")
        private String email;
        @Pattern(groups = {EmailMode.class}, regexp = RegexpConstant.VERIFY_CODE, message = "请输入6位验证码")
        @NotBlank(groups = {EmailMode.class}, message = "验证码不能为空")
        @ApiModelProperty(value = "验证码")
        private String verifyCode;
    }

    @Data
    @ApiModel(description = "手机号模式")
    public static class PhoneMode {
        @Pattern(groups = {PhoneMode.class}, regexp = RegexpConstant.PHONE, message = "请输入正确的手机号码")
        @NotBlank(groups = {PhoneMode.class}, message = "手机号码不能为空")
        @ApiModelProperty(value = "手机号码")
        private String phone;
        @Pattern(groups = {PhoneMode.class}, regexp = RegexpConstant.VERIFY_CODE, message = "请输入6位验证码")
        @NotBlank(groups = {PhoneMode.class}, message = "验证码不能为空")
        @ApiModelProperty(value = "验证码")
        private String verifyCode;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Mode implements ICode {
        UseUsername("use_username"),
        UsePhone("use_phone"),
        UseEmail("use_email");
        private final String code;
    }
}
