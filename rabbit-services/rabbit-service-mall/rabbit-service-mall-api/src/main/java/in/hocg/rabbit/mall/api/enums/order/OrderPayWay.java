package in.hocg.rabbit.mall.api.enums.order;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2022/1/25
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = MallDataDictKeys.ORDER_PAY_WAY, description = "支付方式")
public enum OrderPayWay implements DataDictEnum {
    Manual("manual", "手动支付");
    private final String code;
    private final String name;


}
