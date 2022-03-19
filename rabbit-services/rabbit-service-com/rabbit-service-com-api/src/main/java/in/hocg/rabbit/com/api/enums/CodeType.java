package in.hocg.rabbit.com.api.enums;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.com.api.named.ComDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = ComDataDictKeys.CODE_TYPE, description = "序列号类型")
public enum CodeType implements DataDictEnum {
    Order("O", "订单"),
    MaintainOrder("M", "售后单"),
    DeliveryOrder("D", "配送单"),
    UserCoupon("U", "用户优惠券"),
    ShareContent("SC", "内容分享"),
    ;
    private final Serializable code;
    private final String name;
}
