package in.hocg.rabbit.ums.api.pojo.ro;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@Setter
@ApiModel
public class RegisterRo {
    @NotBlank(message = "短信序列号不能为空")
    private String serialNo;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;
}
