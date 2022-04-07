package in.hocg.rabbit.mina.api.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class BaseRechargeRo {
    @ApiModelProperty(value = "商户用户", required = true)
    private Long userId;

    @ApiModelProperty("签名字符串")
    private String sign;
}
