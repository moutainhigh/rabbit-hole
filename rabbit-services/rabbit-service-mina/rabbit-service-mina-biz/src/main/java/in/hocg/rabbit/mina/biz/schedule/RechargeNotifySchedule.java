package in.hocg.rabbit.mina.biz.schedule;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import in.hocg.rabbit.mina.api.pojo.vo.RechargeOrderVo;
import in.hocg.rabbit.mina.biz.entity.RechargeOrder;
import in.hocg.rabbit.mina.biz.pojo.dto.Notify;
import in.hocg.rabbit.mina.biz.service.RechargeOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RechargeNotifySchedule {
    public static final int MAX_COUNT = 24;
    private final RechargeOrderService rechargeOrderService;

    @Scheduled(cron = "0 */5 * * * ?")
    public void task() {
        LocalDateTime now = LocalDateTime.now();
        List<RechargeOrder> records = rechargeOrderService.listByWaitNotify(now.minusDays(15), now);
        if (CollUtil.isEmpty(records)) {
            return;
        }
        records.forEach(this::tryNotifyAccess);
    }

    public void tryNotifyAccess(RechargeOrder entity) {
        try {
            rechargeOrderService.notifyAccess(entity);
        } catch (Exception e) {
            log.error("[{}] 通知失败", entity.getId(), e);
        }
    }


    public static LocalDateTime getNextNotifyTime(LocalDateTime prevNotifyAt, Integer count) {
        List<Integer> timesSecond = List.of(5 * 60, 10 * 60, 10 * 60, 30 * 60, 30 * 60);
        Integer second = timesSecond.get(Math.min(count, timesSecond.size()));
        return prevNotifyAt.plusSeconds(second);
    }
}
