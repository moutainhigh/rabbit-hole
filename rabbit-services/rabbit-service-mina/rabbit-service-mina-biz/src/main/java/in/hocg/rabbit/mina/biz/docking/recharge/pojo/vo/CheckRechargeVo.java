package in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;
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
public class CheckRechargeVo {
    @ApiModelProperty("系统定单号")
    @JSONField(name = "order_number")
    private String orderNumber;
    @ApiModelProperty("商户订单号")
    @JSONField(name = "out_trade_num")
    private String outTradeNum;
    @ApiModelProperty("充值手机号")
    private String mobile;
    @ApiModelProperty("产品ID")
    @JSONField(name = "product_id")
    private String productId;
    @ApiModelProperty("下单时间")
    @JSONField(name = "create_time")
    private String createTime;
    @ApiModelProperty("充值成功面额")
    @JSONField(name = "charge_amount")
    private String chargeAmount;
    @ApiModelProperty("状态")
    @JSONField(name = "state")
    private String state;

    public boolean isSuccess() {
        return "1".equals(state);
    }
    public boolean isFail() {
        return "2".equals(state);
    }
}
