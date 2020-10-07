package com.github.lotus.chaos.modules.ums.ro;

import io.swagger.annotations.ApiModel;
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
    private String phone;
    private String nickname;
    private String password;
    private String sms;
}
