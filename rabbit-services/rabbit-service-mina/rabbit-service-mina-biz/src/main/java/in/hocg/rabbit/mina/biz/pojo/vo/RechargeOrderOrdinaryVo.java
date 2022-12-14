package in.hocg.rabbit.mina.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.rabbit.common.constant.DataDictKeys;
import in.hocg.rabbit.mina.api.named.MinaDataDictKeys;
import in.hocg.rabbit.mina.api.named.MinaNamed;
import in.hocg.rabbit.mina.api.named.MinaNamedType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@Accessors(chain = true)
public class RechargeOrderOrdinaryVo {
    @ApiModelProperty("内部订单号")
    private String orderNo;
    @ApiModelProperty("外部订单号")
    private String outOrderNo;
    @ApiModelProperty("产品编号")
    private String productId;
    @ApiModelProperty("产品名称")
    private String productName;
    @ApiModelProperty("充值账号")
    private String account;
    @ApiModelProperty("通知地址")
    private String notifyUrl;
    @ApiModelProperty("扣费金额")
    private BigDecimal totalAmt;

    @ApiModelProperty("结束通知时间")
    private LocalDateTime finishNotifyAt;
    @ApiModelProperty("下一次通知时间")
    private LocalDateTime nextNotifyAt;
    @ApiModelProperty("已通知次数")
    private Integer notifyRecount;
    @ApiModelProperty("最后一次通知结果")
    private String lastNotifyResult;

    @ApiModelProperty("任务状态")
    private String status;
    @MinaNamed(idFor = "status", type = MinaNamedType.DataDictName, args = {MinaDataDictKeys.RECHARGE_ORDER_STATUS})
    private String statusName;
    @ApiModelProperty("失败原因")
    private String failReason;
}
