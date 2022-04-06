package in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class RechargeVo {
    @ApiModelProperty("系统定单号")
    @JSONField(name = "order_number")
    private String orderNumber;
    @ApiModelProperty("充值手机号")
    private String mobile;
    @ApiModelProperty("产品ID")
    @JSONField(name = "product_id")
    private String productId;
    @ApiModelProperty("消费金额")
    @JSONField(name = "total_price")
    private String totalPrice;
    @ApiModelProperty("商户订单号")
    @JSONField(name = "out_trade_num")
    private String outTradeNum;
    @ApiModelProperty("充值产品说明")
    private String title;
}
