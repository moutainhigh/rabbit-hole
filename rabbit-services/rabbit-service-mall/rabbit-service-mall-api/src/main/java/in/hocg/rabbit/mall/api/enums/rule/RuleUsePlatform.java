package in.hocg.rabbit.mall.api.enums.rule;

import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.enums.order.OrderedSource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum RuleUsePlatform implements DataDictEnum {
    None("none", "不限制"),
    Unknown(OrderedSource.Unknown.getCodeStr(), OrderedSource.Unknown.getName()),
    ;
    private final String code;
    private final String name;
}
