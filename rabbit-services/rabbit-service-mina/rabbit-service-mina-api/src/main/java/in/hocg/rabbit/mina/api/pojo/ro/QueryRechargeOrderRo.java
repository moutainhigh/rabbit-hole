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
public class QueryRechargeOrderRo extends BaseRechargeRo {
    @NotNull(message = "外部单号不能为空")
    @ApiModelProperty(value = "外部单号", required = true)
    private String outOrderNo;
}
