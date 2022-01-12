package in.hocg.rabbit.mall.biz.support.mode;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 * -> 满足所有规则
 *
 * @author hocgin
 */
@Data
@RequiredArgsConstructor
public abstract class Discount<C extends OrderContext> {
    private final Serializable id;
    private final List<Rule<C>> rules;

    public Discount(Serializable id) {
        this(id, Collections.emptyList());
    }

    private boolean used = false;
    private BigDecimal discountAmt = BigDecimal.ZERO;

    private DiscountResult matched(C context) {
        return handle(context, new DiscountResult(rules.stream().map(rule -> rule.matched(context)).collect(Collectors.toList()), this));
    }

    /**
     * 加工匹配结果
     *
     * @param result
     * @return
     */
    public DiscountResult handle(C context, DiscountResult result) {
        return result;
    }

    /**
     * 匹配规则
     *
     * @param context
     * @param used
     * @return 是否满足期望
     */
    public boolean matched(C context, boolean used) {
        DiscountResult result = matched(context);
        context.putDiscountResult(id, result);
        boolean conform = true;
        this.used = used;
        if (used & result.getUsable()) {
            context.readjustment();
        } else if (used & !result.getUsable()) {
            conform = false;
        }
        return conform;
    }

}
