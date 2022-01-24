package in.hocg.rabbit.com.api.enums.integralflow;

import in.hocg.boot.utils.enums.DataDictEnum;
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
public enum ChangeType implements DataDictEnum {
    Plus("plus", "新增"),
    Subtract("subtract", "减少");
    private final Serializable code;
    private final String name;

    public final static String KEY = "integral_flow_change_type";
}

