package in.hocg.rabbit.mall.biz.support.helper.order2.rule.rule;



import cn.hutool.core.util.StrUtil;
import in.hocg.rabbit.mall.biz.support.helper.order2.modal.GeneralOrder;
import in.hocg.rabbit.mall.biz.support.helper.order2.modal.GeneralProduct;
import in.hocg.rabbit.mall.biz.support.helper.order2.rule.AbsRule;
import in.hocg.rabbit.mall.biz.support.helper.order2.rule.CheckResult;

import java.util.List;

/**
 * Created by hocgin on 2020/7/14.
 * email: hocgin@gmail.com
 * 指定平台
 *
 * @author hocgin
 */
public class UsablePlatformRule extends AbsRule<GeneralOrder> {
    private final String platform;

    public UsablePlatformRule(String platform) {
        this.platform = platform;
    }

    @Override
    public CheckResult check(GeneralOrder context) {
        final List<GeneralProduct> allProducts = context.getProducts();
        if (StrUtil.equals(platform, context.getOrderSource())) {
            return success(allProducts);
        }

        return fail(allProducts, "下单平台不符合");
    }
}
