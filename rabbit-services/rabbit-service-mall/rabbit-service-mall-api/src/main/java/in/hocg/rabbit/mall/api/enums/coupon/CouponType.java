package in.hocg.rabbit.mall.api.enums.coupon;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
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
@UseDataDictKey(value = MallDataDictKeys.COUPON_TYPE, description = "优惠券类型")
public enum CouponType implements DataDictEnum {
    FixedAmt("fixed_amt", "满减"),
    ScaleAmt("scale_amt", "折扣"),
    ;
    private final Serializable code;
    private final String name;

}

