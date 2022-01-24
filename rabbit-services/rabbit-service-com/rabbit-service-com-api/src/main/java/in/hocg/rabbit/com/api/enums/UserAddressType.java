package in.hocg.rabbit.com.api.enums;

import in.hocg.boot.utils.enums.DataDictEnum;
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
public enum UserAddressType implements DataDictEnum {
    Receiver("receiver", "收货地址"),
    Delivery("delivery", "发货地址"),
    Refund("refund", "退货地址"),
    ;
    private final Serializable code;
    private final String name;
}
