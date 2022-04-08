package in.hocg.rabbit.mina.biz.schedule;

import cn.hutool.core.collection.CollUtil;
import in.hocg.rabbit.mina.biz.entity.RechargeOrder;
import in.hocg.rabbit.mina.biz.service.RechargeOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RechargeQuerySchedule {
    private final RechargeOrderService rechargeOrderService;

    @Scheduled(cron = "0 */5 * * * ?")
    public void task() {
        LocalDateTime now = LocalDateTime.now();
        List<RechargeOrder> records = rechargeOrderService.listByExecutingAndCreatedAt(now.minusDays(15), now);
        if (CollUtil.isEmpty(records)) {
            return;
        }
        records.forEach(rechargeOrderService::tryHandleRechargeResult);
    }
}
