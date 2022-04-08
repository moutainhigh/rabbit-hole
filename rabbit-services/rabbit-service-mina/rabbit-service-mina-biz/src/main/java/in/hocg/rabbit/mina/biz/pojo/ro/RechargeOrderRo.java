package in.hocg.rabbit.mina.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class RechargeOrderRo extends BaseRechargeRo {
    @NotNull(message = "外部单号不能为空")
    @ApiModelProperty(value = "外部单号", required = true)
    private String outOrderNo;
    @NotNull(message = "产品编号不能为空")
    @ApiModelProperty(value = "产品编号", required = true)
    private String productId;
    @NotNull(message = "充值账号不能为空")
    @ApiModelProperty(value = "充值账号", required = true)
    private String account;
    @ApiModelProperty(value = "最大成本(如果需要的话)")
    private BigDecimal maxCostAmt;
    @ApiModelProperty(value = "回调地址(如果需要的话)，用于接收充值状态回调")
    private String notifyUrl;
}
