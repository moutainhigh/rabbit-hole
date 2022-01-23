package in.hocg.rabbit.mall.api.enums.orderitem;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = MallDataDictKeys.ORDER_ITEM_PRODUCT_TYPE, description = "订单明细商品类型")
public enum OrderItemProductType implements DataDictEnum {
    Sku("sku", "商品规格"),
    ;
    private final Serializable code;
    private final String name;
}
