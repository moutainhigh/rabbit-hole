package in.hocg.rabbit.common.datadict.bmw;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum AccountFlowDire implements DataDictEnum {
    In("in", "入账"),
    Out("out", "出账"),
    ;
    private final Serializable code;
    private final String name;
}
