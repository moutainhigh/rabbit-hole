package in.hocg.rabbit.common.datadict.bmw;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum PaymentMchType implements DataDictEnum {
    Alipay("alipay", "支付宝"),
    Wxpay("wxpay", "微信支付");
    private final Serializable code;
    private final String name;
}
