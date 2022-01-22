package in.hocg.rabbit.mall.api.enums.order;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = MallDataDictKeys.ORDER_DELIVERY_STATUS, description = "发货状态")
public enum OrderDeliveryStatus implements DataDictEnum {
    NotShipped("not_shipped", "未发货"),
    Shipped("shipped", "已发货"),
    ;
    private final Serializable code;
    private final String name;
}
