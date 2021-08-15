package com.github.lotus.bmw.biz.support.payment;

import in.hocg.payment.PaymentService;
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
public class ConfigStorageDto {
    private String paymentMchType;

    private PaymentService payService;
}
