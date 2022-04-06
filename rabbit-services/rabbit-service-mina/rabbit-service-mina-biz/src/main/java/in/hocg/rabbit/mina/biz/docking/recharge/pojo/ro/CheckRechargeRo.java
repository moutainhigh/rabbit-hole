package in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.rabbit.mina.biz.support.recharge.RechargeHelper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class CheckRechargeRo extends BaseRo {
    @ApiModelProperty("账户ID")
    private String userid = RechargeHelper.USER_ID;
    @ApiModelProperty("商户订单号；多个用英文,分割")
    @JSONField(name = "out_trade_nums")
    private String outTradeNums;
}
