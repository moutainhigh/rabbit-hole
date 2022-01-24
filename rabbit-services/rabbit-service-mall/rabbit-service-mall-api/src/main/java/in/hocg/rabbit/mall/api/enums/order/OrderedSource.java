package in.hocg.rabbit.mall.api.enums.order;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = MallDataDictKeys.ORDERED_SOURCE, description = "订单来源")
public enum OrderedSource implements DataDictEnum {
    Unknown("unknown", "未知"),
    ;
    private final Serializable code;
    private final String name;
}
