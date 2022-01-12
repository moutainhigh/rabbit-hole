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
@UseDataDictKey(description = "收货状态")
public enum OrderReceiveStatus implements DataDictEnum {
    NotReceived("not_received", "未收货"),
    Received("received", "已收货"),
    ;
    private final Serializable code;
    private final String name;
}
