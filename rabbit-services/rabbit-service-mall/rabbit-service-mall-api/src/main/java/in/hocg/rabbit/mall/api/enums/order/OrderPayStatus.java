package in.hocg.rabbit.mall.api.enums.order;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
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
@UseDataDictKey(description = "支付状态")
public enum OrderPayStatus implements DataDictEnum {
    NotPayed("not_payed", "未支付"),
    Payed("payed", "已支付"),
    Timeout("timeout", "支付超时"),
    Fail("fail", "支付失败"),
    ;
    private final Serializable code;
    private final String name;
}
