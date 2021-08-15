package com.github.lotus.bmw.biz.docking;

import com.github.lotus.bmw.api.pojo.ro.PayTradeRo;
import com.github.lotus.bmw.api.pojo.vo.PayTradeVo;
import com.github.lotus.bmw.biz.entity.PayRecord;
import com.github.lotus.bmw.biz.entity.PaymentMch;
import in.hocg.payment.PaymentService;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface PaymentMchService {
    PayTradeVo goPay(PayTradeRo ro);

    String goRefund(Long refundRecordId);

    PayRecord getTradeWithPayRecord(String paymentMchCode, String ro);

    default PaymentService<?> getPayService(PaymentMch paymentMch) {
        return null;
    }
}
