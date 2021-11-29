package in.hocg.rabbit.bmw.biz.docking;

import in.hocg.rabbit.bmw.biz.pojo.ro.GoPayRo;
import in.hocg.rabbit.bmw.biz.pojo.vo.GoPayVo;
import in.hocg.rabbit.bmw.biz.entity.PayRecord;
import in.hocg.rabbit.bmw.biz.entity.PaymentMch;
import in.hocg.rabbit.bmw.biz.support.payment.helper.RequestHelper;
import in.hocg.payment.PaymentService;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface PaymentMchDockingService2 {
    /**
     * 发起支付
     *
     * @param ro 支付参数
     * @return 处理参数
     */
    GoPayVo goPay(GoPayRo ro);

    /**
     * 关闭交易
     *
     * @param tradeOrderId 交易单
     */
    void closeTrade(Long tradeOrderId);

    /**
     * 发起退款
     *
     * @param refundRecordId 退款单
     */
    void goRefund(Long refundRecordId);

    /**
     * 通知结果
     */
    void notifySuccess();

    PayRecord getTradeWithPayRecord(String paymentMchCode, Long payRecordId, String ro);

    default PaymentService<?> getPayService(PaymentMch paymentMch) {
        return RequestHelper.getPayService(paymentMch);
    }
}
