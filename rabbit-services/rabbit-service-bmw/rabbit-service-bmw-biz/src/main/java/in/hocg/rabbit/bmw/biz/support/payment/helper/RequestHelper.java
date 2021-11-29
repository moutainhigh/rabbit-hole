package in.hocg.rabbit.bmw.biz.support.payment.helper;

import in.hocg.rabbit.bmw.biz.entity.PaymentMch;
import in.hocg.rabbit.common.datadict.bmw.PaymentMchType;
import in.hocg.rabbit.common.utils.Rules;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.payment.ConfigStorage;
import in.hocg.payment.PaymentService;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.wxpay.v2.WxPayService;
import lombok.experimental.UtilityClass;

import java.util.Optional;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class RequestHelper {

    public static PaymentService<?> getPayService(PaymentMch paymentMch) {
        ConfigStorage configStorage = ConfigHelper.createConfigStorage(paymentMch);
        Optional<PaymentService<?>> opt = Rules.create()
            // 支付宝
            .rule(PaymentMchType.Alipay, Rules.Supplier(() -> PaymentServices.createPaymentService(AliPayService.class, configStorage)))
            // 微信
            .rule(PaymentMchType.Wxpay, Rules.Supplier(() -> PaymentServices.createPaymentService(WxPayService.class, configStorage)))
            .of(ICode.ofThrow(paymentMch.getType(), PaymentMchType.class));
        return opt.orElseThrow(UnsupportedOperationException::new);
    }

}
