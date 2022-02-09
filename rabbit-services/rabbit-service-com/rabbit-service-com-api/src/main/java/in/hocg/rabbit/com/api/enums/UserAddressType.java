package in.hocg.rabbit.com.api.enums;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.com.api.named.ComDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = ComDataDictKeys.USER_ADDRESS_TYPE, description = "用户地址类型")
public enum UserAddressType implements DataDictEnum {
    Receiver("receiver", "收货地址"),
    Delivery("delivery", "发货地址"),
    Refund("refund", "退货地址"),
    ;
    private final Serializable code;
    private final String name;
}
