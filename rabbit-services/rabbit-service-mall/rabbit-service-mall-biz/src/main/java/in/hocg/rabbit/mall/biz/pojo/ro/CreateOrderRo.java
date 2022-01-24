package in.hocg.rabbit.mall.biz.pojo.ro;

import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2020/3/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateOrderRo extends CalcOrderRo {
    @NotNull(message = "收货人信息错误")
    @ApiModelProperty("收货人信息")
    private CalcOrderVo.UserAddressVo receiver;

    @ApiModelProperty("订单备注")
    private String remark;

}
