package in.hocg.rabbit.mina.biz.pojo.vo;

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
public class RechargeOrderVo {
    @ApiModelProperty(value = "平台编号", required = true)
    private String userId;
    @ApiModelProperty(value = "外部单号", required = true)
    private String outOrderNo;
    @ApiModelProperty(value = "产品编号", required = true)
    private String productId;
    @ApiModelProperty(value = "充值账号", required = true)
    private String account;
    @ApiModelProperty(value = "产品金额")
    private BigDecimal productAmt;
}
