package in.hocg.rabbit.common.datadict.bmw;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum TradeOrderStatus implements DataDictEnum {
    Processing("processing", "进行中"),
    Payed("payed", "已支付"),
    Cancelled("cancelled", "已取消"),
    Closed("closed", "已关闭");
    private final Serializable code;
    private final String name;
}
