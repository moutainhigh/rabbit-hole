package in.hocg.rabbit.bmw.biz.support.payment;

import in.hocg.rabbit.bmw.biz.entity.PaymentMch;
import in.hocg.rabbit.bmw.biz.support.payment.helper.RequestHelper;
import in.hocg.payment.PaymentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor
public class ConfigStorageDto {
    private final PaymentMch paymentMch;

    public PaymentService getPayService() {
        return RequestHelper.getPayService(paymentMch);
    }
}
