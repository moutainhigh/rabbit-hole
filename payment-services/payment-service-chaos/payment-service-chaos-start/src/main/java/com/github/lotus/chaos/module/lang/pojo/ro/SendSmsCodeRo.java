package com.github.lotus.chaos.module.lang.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel("发送验证码")
public class SendSmsCodeRo {
    @NotBlank(message = "手机号码错误")
    @ApiModelProperty("手机号码")
    private String phone;
}
