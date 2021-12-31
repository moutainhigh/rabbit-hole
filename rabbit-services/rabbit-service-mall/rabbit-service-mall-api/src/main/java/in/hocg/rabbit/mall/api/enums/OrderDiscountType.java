package in.hocg.rabbit.mall.api.enums;

import in.hocg.boot.utils.enums.DataDictEnum;
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
public enum OrderDiscountType implements DataDictEnum {
    Coupon("coupon", "优惠券");
    private final String code;
    private final String name;


}
