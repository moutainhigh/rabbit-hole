package com.github.lotus.sso.pojo.ro;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@ApiModel
@Data
public class JoinRo {
    @Pattern(regexp = "^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$", message = "请输入正确的手机号码")
    @NotBlank(message = "手机号码不能为空")
    private String phone;
    @Pattern(regexp = "^\\w{6,12}$", message = "用户名仅支持6~12位的字母、数字或下划线")
    @NotBlank(message = "用户名不能为空")
    private String username;
    @Pattern(regexp = "^.{6,32}$", message = "请输入6~32位的密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    @Pattern(regexp = "^\\d{6}$", message = "请输入6位验证码")
    @NotBlank(message = "验证码不能为空")
    private String sms;
}
