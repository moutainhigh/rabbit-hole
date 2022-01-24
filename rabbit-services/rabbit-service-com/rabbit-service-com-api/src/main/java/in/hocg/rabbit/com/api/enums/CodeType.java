package in.hocg.rabbit.com.api.enums;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum CodeType implements DataDictEnum {
    Order("O", "订单"),
    MaintainOrder("M", "售后单"),
    DeliveryOrder("D", "配送单"),
    ;
    private final Serializable code;
    private final String name;
}
