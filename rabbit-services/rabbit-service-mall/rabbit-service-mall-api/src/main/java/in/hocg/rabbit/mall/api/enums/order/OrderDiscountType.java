package in.hocg.rabbit.mall.api.enums.order;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2020/7/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = MallDataDictKeys.ORDER_DISCOUNT_TYPE, description = "订单优惠类型")
public enum OrderDiscountType implements DataDictEnum {
    UserCoupon("user_coupon", "优惠券");
    private final String code;
    private final String name;


}
