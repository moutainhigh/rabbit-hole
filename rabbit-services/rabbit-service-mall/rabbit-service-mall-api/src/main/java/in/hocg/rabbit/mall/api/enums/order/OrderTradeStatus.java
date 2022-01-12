package in.hocg.rabbit.mall.api.enums.order;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(description = "交易状态")
public enum OrderTradeStatus implements DataDictEnum {
    WaitPay("wait_pay", "待支付"),
    WaitShip("wait_ship", "待发货"),
    WaitReceived("wait_received", "待收货"),
    Success("success", "交易完成"),
    Closed("closed", "交易关闭"),
    ;
    private final Serializable code;
    private final String name;

}
