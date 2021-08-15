package com.github.lotus.bmw.biz.support.payment.helper;

import com.github.lotus.common.datadict.pay.NotifyStatus;
import com.github.lotus.common.datadict.pay.NotifyType;
import com.github.lotus.common.datadict.pay.PayMode;
import com.github.lotus.common.datadict.pay.PaymentPlatform;
import com.github.lotus.common.datadict.pay.RefundStatus;
import com.github.lotus.common.datadict.pay.TradeStatus;
import in.hocg.boot.utils.enums.ICode;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class PaymentHelper {
    /**
     * 最大通知次数
     */
    public static final int MAX_NOTIFY_COUNT = 25;
    /**
     * 成功通知标记
     */
    public static final String SUCCESS_NOTIFY_FLAG = "SUCCESS";

    public static String toWxPayAmount(BigDecimal v) {
        BigDecimal decimal = BigDecimal.valueOf(100L).multiply(v);
        return  decimal.setScale(0, RoundingMode.DOWN).toPlainString();
    }

    public static String toPayMode(PayMode payMode) {
        return payMode.getCode();
    }

    public static String toTradeStatus(TradeStatus tradeStatus) {
        return tradeStatus.getCode();
    }
    public static TradeStatus aliPayAsTradeStatus(String tradeStatus) {
        switch (tradeStatus) {
            case "TRADE_SUCCESS":
                return TradeStatus.Success;
            case "TRADE_CLOSED":
                return TradeStatus.Close;
            default:
                return TradeStatus.Fail;
        }
    }

    public static String toRefundStatus(RefundStatus refundStatus) {
        return refundStatus.getCode();
    }

    public static NotifyStatus tradeStatusToNotifyStatus(String tradeStatusCode) {
        switch (ICode.ofThrow(tradeStatusCode, TradeStatus.class)) {
            case Fail:
                return NotifyStatus.Fail;
            case Init:
                return NotifyStatus.Init;
            case Success:
                return NotifyStatus.Success;
            case Pending:
                return NotifyStatus.Pending;
            case Close:
                return NotifyStatus.Close;
        }
        throw new UnsupportedOperationException();
    }

    public static NotifyStatus refundStatusToNotifyStatus(String refundStatusCode) {
        switch (ICode.ofThrow(refundStatusCode, RefundStatus.class)) {
            case Fail:
                return NotifyStatus.Fail;
            case Init:
                return NotifyStatus.Init;
            case Success:
                return NotifyStatus.Success;
            case Pending:
                return NotifyStatus.Pending;
            case Close:
                return NotifyStatus.Close;
        }
        throw new UnsupportedOperationException();
    }

    public static NotifyType toNotifyType(String notifyType) {
        return ICode.ofThrow(notifyType, NotifyType.class);
    }

    public static PaymentPlatform asPaymentPlatform(String refType) {
        return ICode.ofThrow(refType, PaymentPlatform.class);
    }

    public static TradeStatus wxPayAsTradeStatus(String resultCode) {
        switch (resultCode.toUpperCase()) {
            // 退款成功
            case "SUCCESS": {
                return TradeStatus.Success;
            }
            // 退款关闭
            case "FAIL":
            default:
                return TradeStatus.Fail;
        }
    }

    public static PayMode wxPayAsPayMode(String tradeType) {
        switch (tradeType.toUpperCase()) {
            case "JSAPI":
                return PayMode.WxPayWithJSAPI;
            case "NATIVE":
                return PayMode.WxPayWithNative;
            case "APP":
                return PayMode.WxPayWithApp;
            default:
                return PayMode.Unknown;

        }
    }

    public static RefundStatus wxpayAsRefundStatus(String refundStatus) {
        switch (refundStatus.toUpperCase()) {
            // 退款成功
            case "SUCCESS":{
                return RefundStatus.Success;
            }
            // 退款关闭
            case "REFUNDCLOSE":
                // 退款异常
            case "CHANGE": {
                return RefundStatus.Fail;
            }
            default:
                throw new UnsupportedOperationException("操作失败");
        }
    }
}
