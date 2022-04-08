package in.hocg.rabbit.mina.api.enums;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mina.api.named.MinaDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2022/2/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = MinaDataDictKeys.RECHARGE_ORDER_STATUS, description = "充值单据状态")
public enum RechargeOrderStatus implements DataDictEnum {
    Executing("executing", "执行中"),
    Success("success", "成功"),
    Failed("failed", "失败"),
    ;
    private final String code;
    private final String name;
}
