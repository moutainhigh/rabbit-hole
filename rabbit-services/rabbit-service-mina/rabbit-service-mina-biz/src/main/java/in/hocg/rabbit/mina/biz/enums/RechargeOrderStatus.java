package in.hocg.rabbit.mina.biz.enums;

import in.hocg.boot.utils.enums.DataDictEnum;
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
public enum RechargeOrderStatus implements DataDictEnum {
    Executing("executing", "执行中"),
    Success("success", "成功"),
    Failed("failed", "失败"),
    ;
    private final String code;
    private final String name;
}
