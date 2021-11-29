package in.hocg.rabbit.wl.biz.enumns;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2020/11/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum ShippingMethods implements DataDictEnum {
    Direct("direct", "直达"),
    Transit("transit", "中转");
    private final Serializable code;
    private final String name;

    public static final String KEY = "wl_shipping_methods";
}

