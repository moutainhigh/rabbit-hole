package com.github.lotus.sso.pojo.ro;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created by hocgin on 2020/12/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class SendSmsCodeRo {
    @Pattern(regexp = "^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$", message = "请输入正确的手机号码")
    @NotBlank(message = "手机号码不能为空")
    String phone;
}
