package in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.rabbit.mina.biz.support.recharge.RechargeHelper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class RechargeRo extends BaseRo {
    @NotNull
    @JSONField(name = "out_trade_num")
    @ApiModelProperty(value = "商户订单号，由商户自己生成唯一单号。同一商户，不能存在相同单号订单，相同订单号不能提单）", required = true)
    private String outTradeNum;
    @NotNull
    @JSONField(name = "product_id")
    @ApiModelProperty(value = "产品ID（代理后台查看）", required = true)
    private String productId;
    @NotNull
    @ApiModelProperty(value = "充值号码（手机号、电费户、qq号等）", required = true)
    private String mobile;
    @NotNull
    @JSONField(name = "notify_url")
    @ApiModelProperty(value = "回调地址，用于接收充值状态回调", required = true)
    private String notifyUrl;
    @ApiModelProperty(value = "账户ID", required = true)
    private String userid = RechargeHelper.USER_ID;
    @ApiModelProperty("面值，（不传不校验）如果产品的面值与此参数不同，提单驳回")
    private Integer amount;
    @ApiModelProperty("最高成本，（不传不校验）如果产品成本超过这个值，提单驳回")
    private Integer price;
    @ApiModelProperty("电费省份/直辖市，如：四川、北京、上海，(仅电费带此参数)")
    private String area;
    @ApiModelProperty("电费验证三要素，1-身份证后6位，2-银行卡后六位,3-营业执照后六位，仅南网电费带此参数 (仅电费带此参数)")
    private String ytype;
    @JSONField(name = "id_card_no")
    @ApiModelProperty("身份证后6位/银行卡后6位/营业执照后6位，仅南网电费带此参数 (仅电费带此参数)")
    private String idCardNo;
    @ApiModelProperty("地级市名，仅部分南网电费带此参数，是否带此参数需咨询渠道方 (仅电费带此参数)")
    private String city;
}
