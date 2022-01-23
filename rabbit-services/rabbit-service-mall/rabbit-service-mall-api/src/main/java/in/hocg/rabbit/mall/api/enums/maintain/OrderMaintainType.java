package in.hocg.rabbit.mall.api.enums.maintain;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2022/1/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = MallDataDictKeys.ORDER_MAINTAIN_TYPE, description = "售后类型")
public enum OrderMaintainType implements DataDictEnum {
    FullRefund("full_refund", "退货退款"),
    ;
    private final String code;
    private final String name;

}
