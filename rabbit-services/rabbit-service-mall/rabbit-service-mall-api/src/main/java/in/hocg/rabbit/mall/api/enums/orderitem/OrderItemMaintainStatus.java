package in.hocg.rabbit.mall.api.enums.orderitem;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
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
@UseDataDictKey(value = MallDataDictKeys.ORDER_ITEM_MAINTAIN_STATUS, description = "售后状态")
public enum OrderItemMaintainStatus implements DataDictEnum {
    NotStarted("not_started", "未开始"),
    Starting("starting", "允许售后"),
    Processing("processing", "处理中"),
    Expired("expired", "已到期"),
    Maintained("maintained", "已售后"),
    ;
    private final Serializable code;
    private final String name;
}
