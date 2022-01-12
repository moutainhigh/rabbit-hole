package in.hocg.rabbit.mall.api.enums.order;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
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
@UseDataDictKey(description = "退款状态")
public enum OrderRefundStatus implements DataDictEnum {
    NotReturned("not_returned", "未退款"),
    Refunding("refunding", "退款中"),
    Returned("returned", "已退款"),
    ;
    private final String code;
    private final String name;

}
