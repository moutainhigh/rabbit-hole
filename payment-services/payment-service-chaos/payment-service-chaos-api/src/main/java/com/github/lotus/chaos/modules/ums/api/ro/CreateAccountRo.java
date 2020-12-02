package com.github.lotus.chaos.modules.ums.api.ro;

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
@ApiModel
@Data
public class CreateAccountRo {
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("用户名")
    private String username;
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("注册IP")
    private String createdIp;
    @ApiModelProperty("验证码")
    private String sms;
}
