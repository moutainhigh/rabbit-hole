package com.github.lotus.bmw.biz.pojo.dto;

import com.github.lotus.bmw.api.pojo.vo.RefundStatusSyncVo;
import com.github.lotus.bmw.api.pojo.vo.TradeStatusSyncVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class NotifySyncStatusVo {
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
    @ApiModelProperty("退款状态对象")
    private RefundStatusSyncVo refundStatusSync;
}