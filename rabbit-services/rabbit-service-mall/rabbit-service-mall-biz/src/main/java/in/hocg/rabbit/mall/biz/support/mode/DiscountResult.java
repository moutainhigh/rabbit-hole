package in.hocg.rabbit.mall.biz.support.mode;

import cn.hutool.core.collection.CollUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RequiredArgsConstructor
public class DiscountResult {
    @Getter
    private final List<RuleResult> results;
    @Getter
    private final Discount<? extends OrderContext> discount;

    @ApiModelProperty("是否可用")
    public boolean getUsable() {
        return CollUtil.isNotEmpty(results) && results.parallelStream().allMatch(RuleResult::isOk);
    }

    /**
     * 获取所有规则都匹配的订单明细
     *
     * @return
     */
    public List<OrderSupport.Item> getConform() {
        return results.parallelStream().map(RuleResult::getConform)
            .reduce((a, b) -> {
                a.retainAll(b);
                return a;
            }).orElse(Collections.emptyList());
    }

    public List<String> getErrorMessage() {
        return results.stream().map(RuleResult::getReason).collect(Collectors.toList());
    }

    public String getFirstErrorMessage() {
        return results.stream().filter(checkResult -> !checkResult.isOk())
            .map(RuleResult::getReason).findFirst().orElse(null);
    }
}
