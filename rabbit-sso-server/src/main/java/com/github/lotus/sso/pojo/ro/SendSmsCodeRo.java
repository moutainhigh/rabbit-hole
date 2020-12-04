package com.github.lotus.sso.pojo.ro;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by hocgin on 2020/12/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class SendSmsCodeRo {
    @NotBlank(message = "手机号码不能为空")
    String phone;
}
