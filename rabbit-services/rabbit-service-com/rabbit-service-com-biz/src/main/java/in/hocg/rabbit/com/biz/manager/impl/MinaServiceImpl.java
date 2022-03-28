package in.hocg.rabbit.com.biz.manager.impl;

import cn.hutool.core.lang.Assert;
import in.hocg.rabbit.com.biz.manager.MinaService;
import in.hocg.rabbit.com.biz.service.UserIntegralService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/6/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MinaServiceImpl implements MinaService {
    private final UserIntegralService userIntegralService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userSign(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        Boolean hasSign = userIntegralService.exitUserSign(userId, now.toLocalDate());
        Assert.isFalse(hasSign, "今日已签到");
        userIntegralService.triggerUserSign(userId, now);
    }

    @Override
    public void triggerWatchAd(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        Boolean hasUpperLimit = userIntegralService.hasWatchAdUpperLimit(userId, now.toLocalDate());
        Assert.isFalse(hasUpperLimit, "今日观看已满上限");
        userIntegralService.triggerWatchAd(userId, now);
    }
}
