package in.hocg.rabbit.mall.api.enums;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/1/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = MallDataDictKeys.USER_COUPON_STATUS, description = "用户优惠券状态")
public enum UserCouponStatus implements DataDictEnum {
    UnUse("un_use", "未使用"),
    Used("used", "已使用"),
    Cancelled("cancelled", "已取消"),
    ;
    private final Serializable code;
    private final String name;

}
