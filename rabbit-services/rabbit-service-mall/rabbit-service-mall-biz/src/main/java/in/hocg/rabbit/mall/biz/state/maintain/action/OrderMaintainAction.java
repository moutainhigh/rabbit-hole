package in.hocg.rabbit.mall.biz.state.maintain.action;

import cn.hutool.core.lang.Assert;
import in.hocg.rabbit.mall.api.enums.maintain.OrderMaintainStatus;
import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.entity.OrderMaintain;
import in.hocg.rabbit.mall.biz.service.OrderMaintainService;
import in.hocg.rabbit.mall.biz.state.CommonAction;
import in.hocg.rabbit.mall.biz.state.maintain.MaintainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/1/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderMaintainAction extends CommonAction<OrderMaintainStatus, MaintainEvent> {
    private final OrderMaintainService service;

    @Override
    public void execute(StateContext<OrderMaintainStatus, MaintainEvent> context) {
        String targetCode = getTarget(context).getCodeStr();
        String sourceCode = getSource(context).getCodeStr();
        OrderItem entity = getEntity(context);

        OrderMaintain update = new OrderMaintain();
        update.setStatus(targetCode);
        boolean isOk = service.lambdaUpdate().eq(OrderMaintain::getId, entity.getId())
            .eq(OrderMaintain::getStatus, sourceCode).update(update);
        Assert.isTrue(isOk, "更新状态失败");
    }
}
