package com.github.lotus.pay.biz.pojo.vo;

import com.github.lotus.chaos.api.NamedType;
import com.github.lotus.common.datadict.pay.PayMode;
import in.hocg.boot.named.autoconfiguration.annotation.Named;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/2/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class PayRecordOrdinaryVo {
    private Long id;
    @ApiModelProperty("交易账单")
    private Long tradeId;
    @ApiModelProperty("接入平台")
    private Long accessPlatformId;
    @ApiModelProperty("支付方式")
    private String payMode;
    @Named(idFor = "payMode", type = NamedType.DataDict, args = {PayMode.Key})
    private String payModeName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建的IP")
    private String createdIp;
}
