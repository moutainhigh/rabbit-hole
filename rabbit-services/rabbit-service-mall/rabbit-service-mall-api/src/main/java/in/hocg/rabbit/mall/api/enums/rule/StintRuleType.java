package in.hocg.rabbit.mall.api.enums.rule;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/20
 * email: hocgin@gmail.com
 * 可用
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = MallDataDictKeys.STINT_RULE_TYPE, description = "限制规则类型")
public enum StintRuleType implements DataDictEnum {
    AvailablePlatform("available_platform", "可用平台"),
    AvailableCategory("available_category", "可用品类"),
    AvailableProduct("available_product", "可用商品"),
    ;
    private final Serializable code;
    private final String name;

}
