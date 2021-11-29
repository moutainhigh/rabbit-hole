package in.hocg.rabbit.bmw.biz.docking.alipay;

import in.hocg.payment.PaymentResponse;
import in.hocg.payment.alipay.v2.response.AliPayHttpResponse;
import in.hocg.payment.wxpay.v2.response.WxPayXmlResponse;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class AliPayHelper {

    /**
     * 支付宝金额 -> 平台金额
     *
     * @param amount
     */
    public static BigDecimal asAmt(String amount) {
        return new BigDecimal(amount);
    }

    /**
     * 交易单状态是否支付成功
     *
     * @param tradeStatus
     * @return
     */
    public static boolean isPayed(String tradeStatus) {
        return "SUCCESS".equalsIgnoreCase(tradeStatus);
    }

    public static boolean isSuccess(PaymentResponse response) {
        if (response instanceof AliPayHttpResponse) {
            return "10000".equalsIgnoreCase(((AliPayHttpResponse) response).getCode());
        } else if (response instanceof WxPayXmlResponse) {
            return "SUCCESS".equalsIgnoreCase(((WxPayXmlResponse) response).getResultCode());
        }
        return false;
    }

}
