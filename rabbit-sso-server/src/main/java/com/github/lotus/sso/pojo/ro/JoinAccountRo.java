package com.github.lotus.sso.pojo.ro;

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
@ApiModel(description = "新增账户")
public class JoinAccountRo {
    @NotNull(groups = {EmailMode.class, PhoneMode.class}, message = "该注册方式暂不支持")
    @EnumRange(groups = {EmailMode.class, PhoneMode.class}, enumClass = Mode.class, message = "该注册方式暂不支持")
    @ApiModelProperty(value = "模式", required = true)
    private String mode = Mode.UsePhone.getCode();

    @Pattern(regexp = "^\\w{6,12}$", message = "用户名仅支持6~12位的字母、数字或下划线")
    @ApiModelProperty(value = "指定用户名(默认自动生成)")
    private String username;
    @Pattern(regexp = "^.{6,32}$", message = "请输入6~32位的密码")
    @ApiModelProperty(value = "指定密码(默认自动生成)")
    private String password;

    @Valid
    @NotNull(groups = {EmailMode.class})
    @ApiModelProperty(value = "仅邮件方式使用")
    private EmailMode emailMode;

    @Valid
    @NotNull(groups = {PhoneMode.class})
    @ApiModelProperty(value = "仅手机号方式使用")
    private PhoneMode phoneMode;


    @Data
    @ApiModel(description = "邮件模式")
    public static class EmailMode {
        @Email(groups = {EmailMode.class}, message = "请输入正确的邮件地址")
        @NotBlank(groups = {PhoneMode.class}, message = "邮件地址不能为空")
        @ApiModelProperty(value = "邮件地址")
        private String email;
        @Pattern(groups = {EmailMode.class}, regexp = "^\\d{6}$", message = "请输入6位验证码")
        @NotBlank(groups = {EmailMode.class}, message = "验证码不能为空")
        @ApiModelProperty(value = "验证码")
        private String verifyCode;
    }

    @Data
    @ApiModel(description = "手机号模式")
    public static class PhoneMode {
        @Pattern(groups = {PhoneMode.class}, regexp = "^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$", message = "请输入正确的手机号码")
        @NotBlank(groups = {PhoneMode.class}, message = "手机号码不能为空")
        @ApiModelProperty(value = "手机号码")
        private String phone;
        @Pattern(groups = {PhoneMode.class}, regexp = "^\\d{6}$", message = "请输入6位验证码")
        @NotBlank(groups = {PhoneMode.class}, message = "验证码不能为空")
        @ApiModelProperty(value = "验证码")
        private String verifyCode;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Mode implements ICode {
        UsePhone("use_phone"),
        UseEmail("use_email");
        private final String code;
    }
}
