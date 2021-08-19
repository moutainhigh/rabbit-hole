package com.github.lotus.bmw.biz.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class PaymentMchRecordDto {
    private String refType;
    private String bizType;
    private Long refId;
    private Long paymentMchId;
    private String attach;
    private Long logId;
}
