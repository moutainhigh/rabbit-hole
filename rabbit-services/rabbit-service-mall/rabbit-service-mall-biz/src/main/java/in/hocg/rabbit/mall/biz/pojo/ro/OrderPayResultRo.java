package in.hocg.rabbit.mall.biz.pojo.ro;

import in.hocg.rabbit.pay.api.pojo.vo.TradeStatusSyncVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class OrderPayResultRo {
    @ApiModelProperty("同步通知类型")
    private String syncNotifyType;
    @ApiModelProperty("通知时间")
    private LocalDateTime notifyAt;
    @ApiModelProperty("签名类型")
    private String signType = "md5";
    @ApiModelProperty("签名")
    private String sign;
    @ApiModelProperty("版本")
    private String version = "1.0";

    @ApiModelProperty("交易状态对象")
    private TradeStatusSyncVo tradeStatusSync;
}
