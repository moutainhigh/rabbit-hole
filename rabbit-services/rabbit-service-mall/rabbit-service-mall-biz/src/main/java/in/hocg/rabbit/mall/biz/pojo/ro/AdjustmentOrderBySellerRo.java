package in.hocg.rabbit.mall.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class AdjustmentOrderBySellerRo {
    @Min(value = 1, message = "调价金额不能小于1")
    @ApiModelProperty("调价金额")
    private BigDecimal adjustmentAmt;
    @ApiModelProperty(value = "操作人", hidden = true)
    private Long operatorId;
}
