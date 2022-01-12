package in.hocg.rabbit.mall.biz.support.mode;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@RequiredArgsConstructor
public abstract class Rule<T extends OrderContext> {
    private final Serializable id;

    /**
     * 匹配规则
     *
     * @param context 订单上下文
     * @return 匹配结果
     */
    public abstract RuleResult matched(T context);

    public RuleResult result(String reason, List<OrderSupport.Item> conform) {
        return new RuleResult(conform, reason).setRule(this);
    }

    public RuleResult success(List<OrderSupport.Item> conform) {
        return result(null, conform);
    }

    public RuleResult fail(CharSequence template, Object... params) {
        return result(StrUtil.format(template, params), Collections.emptyList());
    }
}
