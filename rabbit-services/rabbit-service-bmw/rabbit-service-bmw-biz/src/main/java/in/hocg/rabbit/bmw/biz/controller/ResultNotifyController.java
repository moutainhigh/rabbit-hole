package in.hocg.rabbit.bmw.biz.controller;

import in.hocg.rabbit.bmw.biz.constant.BmwConstant;
import in.hocg.rabbit.bmw.biz.service.PaymentMchDockingService;
import in.hocg.rabbit.common.datadict.bmw.PaymentMchType;
import in.hocg.boot.utils.enums.ICode;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 * 支付商户结果通知
 *
 * @author hocgin
 */
@Api(tags = {"bmw::第三方结果通知"})
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping(BmwConstant.PAYMENT_PREFIX)
public class ResultNotifyController {
    private final PaymentMchDockingService dockingService;

    @RequestMapping(BmwConstant.PAY_CALLBACK_URI)
    public void payResult(@PathVariable("paymentMchType") String paymentMchType, @PathVariable("paymentMchCode") String paymentMchCode, @PathVariable("payRecordId") Long payRecordId,
                          @RequestBody String requestBody) {
        PaymentMchType mchType = ICode.ofThrow(paymentMchType, PaymentMchType.class);
        dockingService.handlePayResult(mchType, paymentMchCode, payRecordId, requestBody);
    }
}
