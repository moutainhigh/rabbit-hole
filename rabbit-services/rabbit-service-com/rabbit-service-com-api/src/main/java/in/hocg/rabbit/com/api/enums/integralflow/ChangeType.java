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
@UseDataDictKey(value = ComDataDictKeys.INTEGRAL_FLOW_CHANGE_TYPE, description = "积分流水变动类型")
public enum ChangeType implements DataDictEnum {
    Plus("plus", "新增"),
    Subtract("subtract", "减少");
    private final Serializable code;
    private final String name;
}

