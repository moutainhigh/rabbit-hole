package in.hocg.rabbit.com.api.enums.integralflow;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.com.api.named.ComDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/6/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = ComDataDictKeys.INTEGRAL_FLOW_EVENT_TYPE, description = "积分流水事件类型")
public enum EventType implements DataDictEnum {
    UserSign("user_sign", "用户签到"),
    WatchAd("user_ad", "视频观看");
    private final Serializable code;
    private final String name;

}
