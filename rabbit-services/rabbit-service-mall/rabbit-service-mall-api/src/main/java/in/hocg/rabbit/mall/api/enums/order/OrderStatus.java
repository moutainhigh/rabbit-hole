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
@UseDataDictKey(description = "订单状态")
public enum OrderStatus implements DataDictEnum {
    Create("create", "已创建"),
    Success("success", "交易成功"),
    Close("close", "交易关闭"),
    ;
    private final Serializable code;
    private final String name;
}
