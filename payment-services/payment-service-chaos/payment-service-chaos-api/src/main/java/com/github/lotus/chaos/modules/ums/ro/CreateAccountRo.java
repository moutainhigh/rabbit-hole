package com.github.lotus.chaos.modules.ums.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("注册IP")
    private String createdIp;
    @ApiModelProperty("验证码")
    private String sms;
}
