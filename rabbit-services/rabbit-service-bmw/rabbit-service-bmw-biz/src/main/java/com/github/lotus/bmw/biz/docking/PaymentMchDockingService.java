package com.github.lotus.bmw.biz.docking;

import com.github.lotus.bmw.api.pojo.ro.PayTradeRo;
import com.github.lotus.bmw.api.pojo.vo.PayTradeVo;
import com.github.lotus.bmw.biz.entity.PayRecord;
import com.github.lotus.bmw.biz.entity.PaymentMch;
import com.github.lotus.bmw.biz.entity.PaymentMchType;
import com.github.lotus.bmw.biz.support.payment.helper.RequestHelper;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.payment.PaymentService;
import org.apache.commons.lang3.tuple.Triple;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface PaymentMchDockingService {
    PayTradeVo goPay(PayTradeRo ro);

    String goRefund(Long refundRecordId);

    PayRecord getTradeWithPayRecord(String paymentMchCode, Long payRecordId, String ro);

    default PaymentService<?> getPayService(PaymentMch paymentMch) {
        return RequestHelper.getPayService(paymentMch);
    }

    void notifySuccess();
}
