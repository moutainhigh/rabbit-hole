package in.hocg.rabbit.mall.biz.support.mode;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class OrderSupport {
    @ApiModelProperty(value = "订单明细")
    private List<OrderSupport.Item> items = Collections.emptyList();

    @Data
    @Accessors(chain = true)
    @RequiredArgsConstructor
    public static class Item {
        private final Serializable id;
        @ApiModelProperty(value = "[计算型]原总价=销售价格x购买数量", required = true)
        private final BigDecimal totalAmt;
    }

    public <C extends OrderContext> C use(C context, List<? extends Discount<C>> discounts, List<Serializable> usedIds) {
        context.initialization(this, discounts);
        if (CollUtil.isNotEmpty(usedIds)) {
            discounts.forEach(discount -> Assert.isTrue(discount.matched(context, usedIds.contains(discount.getId())), "优惠不满足使用规则"));
        }
        return context;
    }

    public <C extends OrderContext> C use(C context, List<? extends Discount<C>> discounts) {
        return use(context, discounts, Collections.emptyList());
    }

    public <C extends OrderContext> C use(C context) {
        return use(context, Collections.emptyList(), Collections.emptyList());
    }

    public static OrderSupport create(OrderSupport.Item... items) {
        return create(List.of(items));
    }

    public static OrderSupport create(List<OrderSupport.Item> items) {
        return new OrderSupport().setItems(items);
    }
}
