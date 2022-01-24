package in.hocg.rabbit.mall.api.enums.maintain;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2022/1/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = MallDataDictKeys.ORDER_MAINTAIN_STATUS, description = "售后状态")
public enum OrderMaintainStatus implements DataDictEnum {
    Processing("processing", "进行中"),
    Success("success", "成功"),
    Close("close", "关闭"),
    ;
    private final String code;
    private final String name;

}
