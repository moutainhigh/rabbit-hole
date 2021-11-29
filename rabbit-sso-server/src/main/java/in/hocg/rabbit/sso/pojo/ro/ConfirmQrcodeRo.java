package in.hocg.rabbit.sso.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/7/25
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class ConfirmQrcodeRo {
    @ApiModelProperty(value = "标记", required = true)
    private String idFlag;
    @ApiModelProperty(value = "账号", hidden = true)
    private Long userId;
}
